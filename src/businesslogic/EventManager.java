package businesslogic;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> allEvents;
    private Event currentEvent;
    private List<EventManagerReceiver> receivers;

    public EventManager() {
        receivers = new ArrayList<>();
       /* receivers.add(new BaseEventManagerReceiver() {
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
    public List<Event> getAllEvents() {
        if (allEvents == null) {
            allEvents = new ArrayList<>();
            allEvents.addAll(CateringAppManager.dataManager.loadChefEvents(2));
        }

        // Restituisce una copia della propria lista per impedire ad altri oggetti di modificarne
        // il contenuto
        List<Event> ret = new ArrayList<>();
        ret.addAll(allEvents);
        return ret;

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

    public Event getCurrentEvent() {
        return currentEvent;
    }
/*
    public Event chooseMenu(Event m) {
        User u = CateringAppManager.userManager.getCurrentUser();
        if (!u.isChef()) throw new UseCaseLogicException("Solo gli chef possono editare un menu");
        if (m.isInUse()) throw new MenuException("Il menu non può essere modificato perché è in uso");
        if (!m.getOwner().equals(u)) throw new MenuException("Solo il proprietario " + u.toString() + " può modificare il menu");
        this.currentEvent = m;
        return this.currentEvent;
    }

    public Section defineSection(String name) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        Section s = this.currentEvent.addSection(name);

        for (EventManagerReceiver r: receivers) {
            r.notifySectionAdded(currentEvent, s);
        }
        return s;

    }

    public MenuItem insertItem(Recipe rec, Section sec, String desc) {
        if (this.currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!rec.isDish()) throw new UseCaseLogicException(rec.toString() + " non è la ricetta di un piatto finito.");

        if (sec != null && !currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString()
                + " non appartiene al menu corrente.");
        MenuItem it = null;
        if (desc == null || desc.trim().length() == 0) it = currentEvent.addItem(rec, sec);
        else it = currentEvent.addItem(rec, sec, desc);

        if (it != null) {
            for (EventManagerReceiver r: receivers) {
                r.notifyItemAdded(currentEvent, sec, it);
            }
        }
        return it;

    }

    public void publish() {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        this.currentEvent.setPublished(true);
        for (EventManagerReceiver r: receivers) {
            r.notifyMenuPublished(currentEvent);
        }
    }

    public Event copyMenu(Event m) {
        User u = CateringAppManager.userManager.getCurrentUser();
        if (!u.isChef()) throw new UseCaseLogicException("Solo gli chef possono creare un menu");
        else {
            currentEvent = m.clone();
            currentEvent.setPublished(false);
            currentEvent.setOwner(u);
            for (EventManagerReceiver r: receivers) {
                r.notifyMenuCreated(currentEvent);
            }
            return currentEvent;
        }
    }

    public void deleteMenu(Event m) {
        User u = CateringAppManager.userManager.getCurrentUser();
        if (!u.isChef()) throw new UseCaseLogicException("Solo gli chef possono eliminare un menu");
        if (m.isInUse()) throw new MenuException("Il menu non può essere eliminato perché è in uso");
        if (!m.getOwner().equals(u)) throw new MenuException("Solo il proprietario " + u.toString() + " può eliminare il menu");
        for (EventManagerReceiver r: receivers) {
            r.notifyMenuDeleted(m);
        }
    }

    public void deleteSectionWithItems(Section sec) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString() + " non appartiene al menu corrente");
        currentEvent.removeSection(sec, true);
        for (EventManagerReceiver r: receivers) {
            r.notifySectionRemoved(currentEvent, sec);
        }

    }

    public void deleteSection(Section sec) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString() + " non appartiene al menu corrente");
        List<MenuItem> its = sec.getItems();
        currentEvent.removeSection(sec, false);
        for (EventManagerReceiver r: receivers) {
            for (MenuItem it: its) {
                r.notifyItemMoved(currentEvent, sec, null, it);
            }
            r.notifySectionRemoved(currentEvent, sec);
        }
    }

    public void changeSectionName(Section sec, String name) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString() + " non appartiene al menu corrente");
        sec.setName(name);
        for (EventManagerReceiver r: receivers) {
            r.notifySectionNameChanged(currentEvent, sec);
        }
    }

    public void moveSection(Section sec, int pos) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString() + " non appartiene al menu corrente");

        if (pos >= 0 && pos < currentEvent.getSectionCount()) {
            this.currentEvent.moveSection(sec, pos);
            for (EventManagerReceiver r: receivers) {
                r.notifySectionsRearranged(currentEvent);
            }
        }
    }

    public void moveItemsWithoutSection(MenuItem it, int pos) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasItemWithoutSection(it)) throw new UseCaseLogicException("la voce " + it.toString() + " non " +
                "appartiene direttamente al menu corrente");
        if (pos >= 0 && pos < currentEvent.getItemsWithoutSectionCount()) {
            this.currentEvent.moveItemWithoutSection(it, pos);
            for (EventManagerReceiver r: receivers) {
                r.notifyItemsRearrangedInMenu(currentEvent);
            }
        }

    }

    public void moveItemsInSection(Section sec, MenuItem it, int pos) {
        if (currentEvent == null)
            throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!sec.hasItem(it)) throw new UseCaseLogicException("la voce " + it.toString() + " non " +
                "appartiene alla sezione " + sec.toString());
        if (pos >= 0 && pos < sec.getItemsCount()) {
            sec.moveItem(it, pos);
            for (EventManagerReceiver r: receivers) {
                r.notifyItemsRearranged(currentEvent, sec);
            }
        }
    }

    public void assignItemToSection(MenuItem it, Section sec) {
        if (currentEvent == null)
            throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (sec != null && !currentEvent.hasSection(sec)) throw new UseCaseLogicException("la sezione " + sec.toString() +
                " non appartiene al menu corrente");
        Section oldsec = currentEvent.getSection(it);
        if (!currentEvent.hasItemWithoutSection(it) && oldsec==null) throw new UseCaseLogicException("la voce " + it.toString() +
                " non appartiene al menu corrente");
        currentEvent.changeSection(it, sec);

        for (EventManagerReceiver r: receivers) {
            r.notifyItemMoved(currentEvent, oldsec, sec, it);
        }
    }

    public void changeItemDescription(MenuItem it, String newDesc) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasItem(it)) throw new UseCaseLogicException("la voce " + it.toString() + " non " +
                "appartiene al menu corrente");

        it.setDescription(newDesc);
        for (EventManagerReceiver r: receivers) {
            r.notifyItemDescriptionChanged(currentEvent, it);
        }
    }

    public void deleteItem(MenuItem it) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        if (!currentEvent.hasItem(it)) throw new UseCaseLogicException("la voce " + it.toString() + " non " +
                "appartiene al menu corrente");
        currentEvent.removeItem(it);
        for (EventManagerReceiver r: receivers) {
            r.notifyItemDeleted(currentEvent, it);
        }
    }

    public void setMenuTitle(String title) {
        if (currentEvent == null) throw new UseCaseLogicException("non è stato specificato il menu su cui si sta lavorando");
        currentEvent.setTitle(title);
        for (EventManagerReceiver r: receivers) {
            r.notifyMenuTitleChanged(currentEvent);
        }
    }

    public void addReceiver(EventManagerReceiver rec) {
        this.receivers.add(rec);
    }

    public void removeReceiver(EventManagerReceiver rec) {
        this.receivers.remove(rec);
    }
*/
}
