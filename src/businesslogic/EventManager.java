package businesslogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> events=null;
    private Event currentEvent;
    private List<EventManagerReceiver> receivers;
    private ObservableList<Event> obEvent;
    public EventManager() {
        receivers = new ArrayList<>();
        obEvent= FXCollections.observableArrayList();
       /* receivers.add(new BaseEventReceiver() {
            @Override
            public void notifyMenuCreated(Event m) {
                allEvents.add(m);
            }

            @Override
            public void notifyMenuDeleted(Event m) {
                allEvents.remove(m);
            }
        });*/
    };

    // Nota: nell'inizializzazione non carichiamo l'elenco di ricette
    // perché lo faremo "onDemand", ossia se viene richiesto da qualche altro oggetto
    // L'idea è evitare di caricare tutto se non serve.
    public void initialize() {};

    // Questo metodo non è stato descritto nel Class Diagram perché
    // l'UC che abbiamo analizzato partiva dal presupposto che l'utente
    // avesse già davanti la lista dei menu disponibili. Quindi questa
    // parte va aggiunta direttamente nel codice.
  /*  public List<Event> getAllEvents() {
        if (allEvents == null) {
            allEvents = new ArrayList<>();
            allEvents.addAll(CateringAppManager.dataManager.loadChefEvents(CateringAppManager.userManager.getCurrentUser().getUserId()));
        }

        // Restituisce una copia della propria lista per impedire ad altri oggetti di modificarne
        // il contenuto
        List<Event> ret = new ArrayList<>();
        ret.addAll(allEvents);
        return ret;

    }*/
    public ObservableList getObservableEvents(){
        return obEvent;
    }

    public Event createEvent(String title) {
        User u = CateringAppManager.userManager.getCurrentUser();
        if (!u.isChef()) throw new UseCaseLogicException("Solo gli chef possono creare un menu");
        else {
            currentEvent = new Event(title);
           /* for (EventManagerReceiver r: receivers) {
                r.notifyMenuCreated(currentEvent);
            }*/
            return currentEvent;
        }
    }
    public void loadUserEvents(int userId){
        if(events!=null){
            obEvent.removeAll(events);
        }
        events=CateringAppManager.dataManager.loadChefEvents(userId);
        obEvent.addAll(events);
        System.out.println("obEvent: "+obEvent.size());
    }
    public Event getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentEvent(Event e) {
        currentEvent= e;
    }

    public Menu getMenu(){
        return this.currentEvent.getMenu();
    }
    public SummarySheet createSummarySheet(String title){
        SummarySheet ss= currentEvent.createSummarySheet(title);
        return ss;
    }

}
