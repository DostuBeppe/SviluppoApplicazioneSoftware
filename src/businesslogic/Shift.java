package businesslogic;

public class Shift implements Cloneable {

    private User owner;
    private String title;
    private String date;
    private int chefId;
    private int eventId;
    private int menuId;
    private Menu menu;
    public Shift(String title) {
        this.title= title;
    }
    public Shift() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String toString() {
        return this.title ;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public void setMenu(){
        this.menu= CateringAppManager.dataManager.loadMenuEvent(this.menuId);
    }
    // Accessor methods (set/get)
    /*
    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isFingerFood() {
        return fingerFood;
    }

    public void setFingerFood(boolean fingerFood) {
        this.fingerFood = fingerFood;
    }

    public boolean isCookRequired() {
        return cookRequired;
    }

    public void setCookRequired(boolean cookRequired) {
        this.cookRequired = cookRequired;
    }

    public boolean isHotDishes() {
        return hotDishes;
    }

    public void setHotDishes(boolean hotDishes) {
        this.hotDishes = hotDishes;
    }

    public boolean isKitchenRequired() {
        return kitchenRequired;
    }

    public void setKitchenRequired(boolean kitchenRequired) {
        this.kitchenRequired = kitchenRequired;
    }

    public boolean isBuffet() {
        return buffet;
    }

    public void setBuffet(boolean buffet) {
        this.buffet = buffet;
    }

    public User getOwner() {
        return owner;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Section> getSections() {
        List<Section> ret = new ArrayList<>();
        ret.addAll(sections);
        return ret;
    }

    public List<MenuItem> getItemsWithoutSection() {
        List<MenuItem> ret = new ArrayList<>();
        ret.addAll(itemsWithoutSection);
        return ret;
    }

    public boolean hasSection(Section sec) {
        return this.sections.contains(sec);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Event clone() {
        Event copia = new Event(this.owner, this.title);
        copia.setPublished(this.published);
        copia.setInUse(false);
        copia.setKitchenRequired(this.kitchenRequired);
        copia.setHotDishes(this.hotDishes);
        copia.setFingerFood(this.fingerFood);
        copia.setCookRequired(this.cookRequired);
        copia.setBuffet(this.buffet);
        for (Section s: this.sections) {
            copia.sections.add(s.clone());
        }
        for (MenuItem it: this.itemsWithoutSection) {
            copia.itemsWithoutSection.add(it.clone());
        }
        return copia;
    }

    public void removeSection(Section sec, boolean deleteItems) {
        if (deleteItems) {
            this.sections.remove(sec);
        } else {
            this.itemsWithoutSection.addAll(sec.getItems());
            this.sections.remove(sec);
        }
    }

    public int getSectionCount() {
        return sections.size();
    }

    public void moveSection(Section sec, int pos) {
        sections.remove(sec);
        sections.add(pos, sec);
    }

    public boolean hasItemWithoutSection(MenuItem it) {
        return this.itemsWithoutSection.contains(it);
    }

    public int getItemsWithoutSectionCount() {
        return this.itemsWithoutSection.size();
    }


    public void moveItemWithoutSection(MenuItem it, int pos) {
        itemsWithoutSection.remove(it);
        itemsWithoutSection.add(pos, it);
    }

    public Section getSection(MenuItem it) {
        for (Section section: sections) {
            if (section.hasItem(it)) return section;
        }
        return null;
    }

    public void changeSection(MenuItem it, Section newSec) {
        if (itemsWithoutSection.contains(it)) itemsWithoutSection.remove(it);
        for (Section sec: sections) {
            if (sec.hasItem(it)) {
                sec.removeItem(it);
                break;
            }
        }

        if (newSec == null) {
            itemsWithoutSection.add(it);
        } else {
            newSec.addItem(it);
        }
    }

    public boolean hasItem(MenuItem it) {
        if (itemsWithoutSection.contains(it)) return true;
        return (this.getSection(it) != null);
    }

    public void removeItem(MenuItem it) {
        if (itemsWithoutSection.contains(it)) itemsWithoutSection.remove(it);
        Section s = this.getSection(it);
        if (s != null) s.removeItem(it);
    }

    public int getSectionPosition(Section s) {
        return sections.indexOf(s);
    }

    public int getItemPosition(MenuItem it) {
        return itemsWithoutSection.indexOf(it);
    }*/
}
