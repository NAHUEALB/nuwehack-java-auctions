package com.hackathon.hackathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<Item> getItemsByType(String type) {
    	return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

	public String makeOffer(String itemName, double amount, Bidder bidder) {
    	return null;
	}

    /**
     * Al invocarlo se filtra el listado de items capturando aquellos que se hayan pujado y luego arma el Map clave
     * y valor del tipo String.
     * @return Map cuya clave es el nombre del item y como valor el nombre del bidder. En caso de no tener bidder
     * se retorna un map vacio.
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
