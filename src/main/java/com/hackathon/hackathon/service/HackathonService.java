package com.hackathon.hackathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.hackathon.model.Bidder;
import com.hackathon.hackathon.model.Item;


/**
 * Para el desarrollo de la prueba:
 * 
 * (La lista de items ya viene inyectada en el servicio procedente del fichero MockDataConfig.java)
 * 
 * - Completa el cuerpo del método getItemsByType(String type) que recibiendo el parámetro type, devuelva una lista de ítems del tipo especificado.
 *
 * - Completa el cuerpo del método makeOffer(String itemName, double amount, Bidder bidder), que al recibir el nombre del ítem (itemName), la cantidad de la oferta (amount), y el postor que realiza la oferta (bidder).
 * 		Comprueba si el ítem especificado por itemName existe en la lista de ítems:
 * 		# Si el ítem no se encuentra, devuelve la constante ITEM_NOT_FOUND.
 * 		# Si el ítem se encuentra, compara la oferta (amount) con la oferta más alta actual del ítem (highestOffer).
 * 			# Si la oferta es mayor que la oferta más alta, actualiza la oferta más alta y el postor actual del ítem y devuelve la constante OFFER_ACCEPTED.
 * 			# Si la oferta es igual o menor que la oferta más alta, devuelve la constante OFFER_REJECTED.
 * 
 * - Completa el cuerpo del método getWinningBidder() que debe devolver un Map de los Items en los que se haya pujado (que existe un Bidder) y cuyo valor sea el nombre del Bidder que ha pujado.
 */

@Service
public class HackathonService {
	
	private static String ITEM_NOT_FOUND = "Item not found";
	private static String OFFER_ACCEPTED = "Offer accepted";
	private static String OFFER_REJECTED = "Offer rejected";

    private List<Item> items;

    @Autowired
    public HackathonService(List<Item> items) {
        this.items = items;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    /**
     * Recupera todos los items de un tipo especifico.
     * @param type Tipo de item a buscar.
     * @return una lista con todos los items que sean del tipo especificado por parametro. En caso de que ningun item
     * sea del tipo especificado se retornara una lista vacia.
     */
    public List<Item> getItemsByType(String type) {
    	return items.stream()
                    .filter(item -> type.equals(item.getType()))
                    .toList();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Genera una oferta a un item del listado.
     * @param itemName Nombre del item al que se le desea realizar la oferta.
     * @param amount Monto que se oferta por el item de la lista.
     * @param bidder Datos de la persona que hizo la oferta.
     * @return Un String con el estado de la oferta(Offer accepted/Offer rejected). En caso de que el item no
     * se encuentre en el listado se devolvera el mensaje "Item not found".
     */
    public String makeOffer(String itemName, double amount, Bidder bidder) {
        Optional<Item> itemSearch = items.stream()
                .filter(item -> itemName.equals(item.getName()))
                .findFirst();

        return itemSearch.map(item -> {
                            if (amount > item.getHighestOffer()) {
                                // Si el monto es mayor a la mayor oferta del item, se actualiza con el monto y su bidder.
                                // Se devolvera el mensaje "Offer accepted".
                                items.replaceAll(itemElement ->{
                                    if(itemName.equals(itemElement.getName())){
                                        itemElement.setHighestOffer(amount);
                                        itemElement.setCurrentBidder(bidder);
                                    }
                                    return itemElement;
                                });
                                return OFFER_ACCEPTED;
                            } else {
                                // Si el monto es menor a la mayor oferta del item, se devolvera el mensaje "Offer rejected".
                                return OFFER_REJECTED;
                            }
                            // En caso de que no exista el item buscado, se devolvera el mensaje "Item not found".
                        }).orElse(ITEM_NOT_FOUND);
    }

    /**
     * Al invocarlo se filtra el listado de items capturando aquellos que se hayan pujado y luego arma el Map clave
     * y valor del tipo String.
     * @return Map cuya clave es el nombre del item y como valor el nombre del bidder. En caso de que ningun item
     * haya sido pujado se retorna un map vacio.
     */
	public Map<String, String> getWinningBidder() {
        return items.stream()
                .filter(item -> item.getCurrentBidder() != null)
                .collect(Collectors.toMap(
                   Item::getName,
                   item -> item.getCurrentBidder().getName()
                ));
    }
}
