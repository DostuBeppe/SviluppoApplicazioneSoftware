package persistence;

import businesslogic.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DataManager {
    private String userName = "root";
    private String password = "1123581321";           //da cambiare
    private String serverName = "localhost";
    private String portNumber = "3306";

//beppeGiovanni
    private Connection connection;

    // Il DataManager deve tener traccia di quali oggetti in memoria
    // corrispondono a quali record del DB. Per questo usa una doppia
    // mappa per ciascun tipo di oggetto caricato
    private Map<User, Integer> userObjects;
    private Map<Integer, User> idToUserObject;

    private Map<Recipe, Integer> recipeObjects;
    private Map<Integer, Recipe> idToRecipeObject;

    private Map<Menu, Integer> menuObjects;
    private Map<Integer, Menu> idToMenuObject;

    private Map<Section, Integer> sectionObjects;
    private Map<Integer, Section> idToSectionObject;

    private Map<MenuItem, Integer> itemObjects;
    private Map<Integer, MenuItem> idToItemObject;


    private Map<Event, Integer> eventObjects;
    private Map<Integer, Event> idToEventObject;

    private Map<Integer, ShiftTask> idToShiftTaskObject;

    private Map<Integer, Shift> idToShiftObject;

    private Map<Integer, Task> idToTaskObject;

    public DataManager() {

        this.userObjects = new HashMap<>();
        this.idToUserObject = new HashMap<>();
        this.recipeObjects = new HashMap<>();
        this.idToRecipeObject = new HashMap<>();
        this.eventObjects = new HashMap<>();
        this.idToEventObject = new HashMap<>();
        this.sectionObjects = new HashMap<>();
        this.idToSectionObject = new HashMap<>();
        this.itemObjects = new HashMap<>();
        this.idToItemObject = new HashMap<>();
        this.idToShiftTaskObject= new HashMap<>();
        this.idToTaskObject= new HashMap<>();
        this.idToShiftObject= new HashMap<>();


    }

    public void initialize() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        connectionProps.put("useUnicode", true);
        connectionProps.put("useJDBCCompliantTimezoneShift", true);
        connectionProps.put("useLegacyDatetimeCode", false);
        connectionProps.put("serverTimezone", "UTC");

        conn = DriverManager.getConnection(
                "jdbc:mysql://"+serverName+":"+portNumber+"/catering",userName,password);

        System.out.println("Connected to database");
        this.connection = conn;


        /*CateringAppManager.menuManager.addReceiver(new EventManagerReceiver() {
            @Override
            public void notifyMenuCreated(Event m) {
                int mid = writeNewMenu(m);
                menuObjects.put(m, mid);
                idToEventObject.put(mid, m);
                List<Section> secs = m.getSections();
                for (int i = 0; i < secs.size(); i++) {
                    Section s = secs.get(i);
                    int sid = writeNewSection(mid, i, s);
                    sectionObjects.put(s, sid);
                    idToSectionObject.put(sid, s);

                    List<MenuItem> secItems = s.getItems();

                    for (int j = 0; j < secItems.size(); j++) {
                        MenuItem it = secItems.get(j);
                        int iid = writeNewItem(mid, sid, j, it);
                        itemObjects.put(it, iid);
                        idToItemObject.put(iid, it);
                    }
                }

                List<MenuItem> menuItems = m.getItemsWithoutSection();
                for (int z=0; z < menuItems.size(); z++) {
                    MenuItem it = menuItems.get(z);
                    int iid = writeNewItem(mid, 0, z, it);
                    itemObjects.put(it, iid);
                    idToItemObject.put(iid, it);
                }
            }

            @Override
            public void notifySectionAdded(Event m, Section s) {
                int mid = menuObjects.get(m);
                int pos = m.getSectionPosition(s);
                int sid = writeNewSection(mid, pos, s);
                sectionObjects.put(s, sid);
                idToSectionObject.put(sid, s);
            }

            @Override
            public void notifyItemAdded(Event m, Section s, MenuItem it) {
                int mid = menuObjects.get(m);
                int sid, pos;
                if (s != null) {
                    sid = sectionObjects.get(s);
                    pos = s.getItemPosition(it);
                } else {
                    sid = 0;
                    pos = m.getItemPosition(it);
                }
                int iid = writeNewItem(mid, sid, pos, it);
                itemObjects.put(it, iid);
                idToItemObject.put(iid, it);
            }

            @Override
            public void notifyMenuPublished(Event m) {
                writeMenuChanges(m);

            }

            @Override
            public void notifyMenuDeleted(Event m) {
                removeMenu(m);
            }

            @Override
            public void notifySectionRemoved(Event m, Section s) {
                removeSection(s);
            }


            @Override
            public void notifySectionNameChanged(Event m, Section s) {
                int mid = menuObjects.get(m);
                int pos = m.getSectionPosition(s);
                writeSectionChanges(mid, pos, s);
            }

            @Override
            public void notifySectionsRearranged(Event m) {
                List<Section> sects = m.getSections();
                int mid = menuObjects.get(m);
                for (int i = 0; i < sects.size(); i++) {
                    writeSectionChanges(mid, i, sects.get(i));
                }

            }

            @Override
            public void notifyItemsRearranged(Event m, Section s) {
                List<MenuItem> its = s.getItems();
                int mid = menuObjects.get(m);
                int sid = sectionObjects.get(s);
                for (int i = 0; i < its.size(); i++) {
                    writeItemChanges(mid, sid, i, its.get(i));
                }
            }

            @Override
            public void notifyItemsRearrangedInMenu(Event m) {    private Map<User, Integer> userObjects;
    private Map<Integer, User> idToUserObject;

    private Map<Recipe, Integer> recipeObjects;
    private Map<Integer, Recipe> idToRecipeObject;

    private Map<Menu, Integer> menuObjects;
    private Map<Integer, Menu> idToMenuObject;

    private Map<Section, Integer> sectionObjects;
    private Map<Integer, Section> idToSectionObject;

    private Map<MenuItem, Integer> itemObjects;
    private Map<Integer, MenuItem> idToItemObject;

                List<MenuItem> its = m.getItemsWithoutSection();
                int mid = menuObjects.get(m);
                for (int i = 0; i < its.size(); i++) {
                    writeItemChanges(mid, 0, i, its.get(i));
                }

            }

            @Override
            public void notifyItemMoved(Event m, Section oldS, Section newS, MenuItem it) {
                int mid = menuObjects.get(m);
                int sid = (newS == null ? 0 : sectionObjects.get(newS));
                int itpos = (newS == null ? m.getItemPosition(it) : newS.getItemPosition(it));
                writeItemChanges(mid, sid, itpos, it);
            }

            @Override
            public void notifyItemDescriptionChanged(Event m, MenuItem it) {
                int mid = menuObjects.get(m);
                Section s = m.getSection(it);
                int sid = (s == null ? 0 : sectionObjects.get(s));
                int itpos = (s == null ? m.getItemPosition(it) : s.getItemPosition(it));
                writeItemChanges(mid, sid, itpos, it);
            }

            @Override
            public void notifyItemDeleted(Event m, MenuItem it) {
                removeItem(it);
            }

            @Override
            public void notifyMenuTitleChanged(Event m) {
                writeMenuChanges(m);
            }
        });*/
    }
    public List<Event> loadChefEvents(int chef) {
        List<Event> ret = new ArrayList<>();
        Statement st = null;
        String query = "SELECT * FROM Events WHERE Events.chef_id="+Integer.toString(chef);

        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                int eventId = rs.getInt("id");
                // Verifica se per caso l'ha già caricato
                Event e = this.idToEventObject.get(eventId);
                if(e==null){
                    e= new Event();
                    String eventName = rs.getString("event_name");
                    int menuId = rs.getInt("menu");
                    String eventDate = rs.getString("event_date");
                    int chefId = rs.getInt("chef_id");
                    System.out.println("event name: "+eventName+" chef id:"+Integer.toString(chefId)+" menu id: "+menuId);
                    e.setChefId(chefId);
                    e.setDate(eventDate);
                    e.setMenuId(menuId);
                    e.setTitle(eventName);
                    e.setEventId(eventId);
                    e.setMenu(loadMenuEvent(menuId));
                    ret.add(e);
                    this.eventObjects.put(e, eventId);
                    this.idToEventObject.put(eventId, e);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return ret;
    }
    public Menu loadMenuEvent(int menuId){
        Menu m=null;
        Statement st = null;
        String query = "SELECT * FROM Menus where Menus.id="+menuId;

        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                // Verifica se per caso l'ha già caricato

                String title = rs.getString("title");

                int ownerid = rs.getInt("menuowner");
                User owner = this.innerLoadUser(ownerid);

                m = new Menu(owner, title);
                m.setPublished(rs.getBoolean("published"));
                m.setBuffet(rs.getBoolean("buffet"));
                m.setCookRequired(rs.getBoolean("cookRequired"));
                m.setFingerFood(rs.getBoolean("fingerFood"));
                m.setHotDishes(rs.getBoolean("hotDishes"));
                m.setKitchenRequired(rs.getBoolean("kitchenRequired"));
                m.setMenuId(id);
                m.setTitle(title);
                // per sapere se il menu è in uso consulto la tabella degli eventi
                // NdR: un menu è in uso anche se l'evento che lo usa è concluso o annullato
                /*Statement st2 = this.connection.createStatement();
                String query2 = "SELECT Events.id FROM Events JOIN Menus M on Events.menu = M.id WHERE M.id=" + id;
                ResultSet rs2 = st2.executeQuery(query2);
                m.setInUse(rs2.next());
                st2.close();*/
                loadMenuSections(id, m);
                loadMenuItems(id,m);



            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return m;
    }
    public User loadUser(String userName) {
        PreparedStatement pst = null;
        String sql = "SELECT Users.id, Users.name, UserRoles.role FROM Users LEFT JOIN UserRoles on Users.id = "
                + "UserRoles.user where Users.name=?";
        User u = null;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (u == null) {
                    u = new User(userName);
                    int id = rs.getInt("id");
                    String role= rs.getString("role");
                    u.setUserId(id);
                    u.setRole(role);
                    this.userObjects.put(u, id);
                    this.idToUserObject.put(id, u);
                }

                //addUserRole(u, rs);

            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return u;
    }
    private void loadMenuItems(int id, Menu m) {
        // Caricamento voci
        // Non verifichiamo se un MenuItem è già stato creato perché
        // questo può avvenire solo nel contesto del caricamento di un Event
        // e il MenuItem può essere già creato solo se il Event è stato creato;
        // il controllo sul Event avviene già in loadMenus
        Statement st = null;
        String query = "SELECT * FROM MenuItems WHERE MenuItems.menu=" +id+" ORDER BY MenuItems.position";
        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String description = rs.getString("description");
                int idSec = rs.getInt("section");
                int idIt = rs.getInt("id");
                int idRec = rs.getInt("recipe");
                Recipe rec = this.innerLoadRecipe(idRec);

                Section sec = null;
                if (idSec > 0) {
                    // la sezione a questo punto dovrebbe essere già stata aggiunta
                    sec = this.idToSectionObject.get(idSec);
                }
                MenuItem it = m.addItem(idIt,rec, sec, description);
                this.itemObjects.put(it, idIt);
                this.idToItemObject.put(idIt, it);
                System.out.println("item loaded id: "+idIt);
            }
            System.out.println("item loaded: "+m.getItemsWithoutSectionCount());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
    }
    public Recipe loadRecipe(int recipeId) {
        Statement st = null;
        Recipe rec=null;
        String query = "SELECT * FROM recipe where recipe.id="+recipeId;
        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                char type = rs.getString("type").charAt(0);
                int id = rs.getInt("id");

                // Verifica se per caso l'ha già caricata
                if (rec == null) {
                    rec = new Recipe(name);
                    rec.setRecipeId(id);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return rec;
    }
    public Map<Integer,Shift> loadShifts(String eventDate){
        PreparedStatement pst = null;
        Shift shift=null;
        String query = "SELECT * FROM shifts where shifts.date="+"\""+eventDate+"\"";
        try {
            pst = this.connection.prepareStatement(query);
            //pst.setString(1, userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int number= rs.getInt("number");
                String date= rs.getString("date");
                String start= rs.getString("start");
                String end= rs.getString("end");
                shift= new Shift(number,id,date,start,end);
                shift.loadStaff(loadStaff(id));
                idToShiftObject.put(id,shift);
                System.out.println("shift number: "+number);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return idToShiftObject;
    }
    public Map<Integer,Staff> loadStaff(int shiftId){
        Statement st = null;
        Shift shift=null;
        Map<Integer,Staff> staffMap= null;
        String query = "select staff_id from rel_shift_staff where rel_shift_staff.shift_id="+shiftId+" and rel_shift_staff.staff_id not in" +
                "(select busy.staff_id from busy join rel_shift_busy on (busy.id=rel_shift_busy.busy_id) where rel_shift_busy.shift_id="+shiftId+")";
        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            staffMap=new HashMap<>();
            while (rs.next()) {
                int staffId=rs.getInt("staff_id");
                staffMap.put(staffId,loadSingleStaff(staffId));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return staffMap;
    }
    public User loadUserFromId(int userId){
        PreparedStatement pst = null;
        String sql = "SELECT Users.id, Users.name FROM Users where Users.id="+userId;
        User u = null;

        try {
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (u == null) {
                    u = new User(userName);
                    int id = rs.getInt("id");
                    String name= rs.getString("name");
                    u.setUserId(id);
                    u.setName(name);
                    this.userObjects.put(u, id);
                    this.idToUserObject.put(id, u);
                }

                //addUserRole(u, rs);

            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return u;
    }
    public Staff loadSingleStaff(int staffId){
        PreparedStatement pst = null;
        String sql = "SELECT * FROM staff where staff.id="+staffId;
        Staff staff = null;

        try {
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                int userId= rs.getInt("user_id");
                int cook= rs.getInt("cook");
                User u= loadUserFromId(userId);
                staff= new Staff();
                staff.setStaffId(id);
                staff.setCook(cook);
                staff.setUserId(userId);
                staff.setName(u.getName());

            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return staff;
    }
    public SummarySheet loadChefSummarySheet(int chefId){
        PreparedStatement pst = null;
        String sql = "select * from summary_sheets where summary_sheets.chef_id=?";
        SummarySheet ss=null;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,chefId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String title= rs.getString("title");
                String note= rs.getString("note");
                int chefIdD= rs.getInt("chef_id");
                ss= new SummarySheet(title);
                ss.setNote(note);
                ss.setChefId(chefIdD);
                ss.setId(id);

            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        loadShiftTask(ss);
        System.out.println("loaded: "+ss.getTitle());
        return ss;
    }
    public void loadShiftTask(SummarySheet ss){
        PreparedStatement pst = null;
        String sql = "select * from shift_task where shift_task.summary_sheet_id=? order by position";
        ShiftTask st=null;
        Map<Integer,ShiftTask> stMap=new HashMap<>();

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,ss.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                int ssId= rs.getInt("summary_sheet_id");
                int position= rs.getInt("position");
                st=new ShiftTask(ssId);
                st.setId(id);
                st.setPosition(position);
                loadShiftST(st);
                loadTaskST(st);
                ss.addShiftTask(st);
                loadStaffST(st);
                System.out.println("added st from db: "+st.getId());
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }

    }
    public void loadShiftST(ShiftTask st){
        PreparedStatement pst = null;
        String sql = "select * from rel_st_shift where rel_st_shift.st_id=?";
        Shift s=null;
        Map<Integer,Shift> shiftMap=new HashMap<>();

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,st.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int stId= rs.getInt("st_id");
                int shiftId= rs.getInt("shift_id");
                shiftMap.put(shiftId,loadShift(shiftId));


            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        st.setShifts(shiftMap);
    }
    public void loadTaskST(ShiftTask st){
        PreparedStatement pst = null;
        String sql = "select * from rel_st_task where rel_st_task.st_id=?";
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,st.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int taskId= rs.getInt("task_id");
                st.setTask(loadTask(taskId));
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
    }
    public void loadStaffST(ShiftTask st){
        PreparedStatement pst = null;
        String sql = "select * from rel_st_staff where rel_st_staff.st_id=?";
        Map<Integer,Staff> staffMap= new HashMap<>();
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,st.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int staffId= rs.getInt("staff_id");

                staffMap.put(staffId,loadSingleStaff(staffId));
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        st.setAllChosenStaff(staffMap);
    }
    public Shift loadShift(int shiftId){
        PreparedStatement pst = null;
        String sql = "select * from shifts where shifts.id=?";
        Shift s=null;
        Map<Integer,Shift> shiftMap=new HashMap<>();

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,shiftId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String date= rs.getString("date");
                String start= rs.getString("start");
                String end= rs.getString("end");
                int number= rs.getInt("number");
                s= new Shift(number,id,date,start,end);
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return s;
    }
    public Task loadTask(int taskId){
        PreparedStatement pst = null;
        String sql = "select * from tasks where tasks.id=?";
        Task t=null;
        Map<Integer,Shift> shiftMap=new HashMap<>();

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,taskId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id= rs.getInt("id");
                String title= rs.getString("title");
                int itemId= rs.getInt("item_id");
                int estimatedTime= rs.getInt("estimated_time");
                int quantity= rs.getInt("quantity");
                int status= rs.getInt("status");
                t=new Task();
                t.setTitle(title);
                t.setEstimatedTime(estimatedTime);
                t.setQuantity(quantity);
                t.setStatus((status==1)?true:false);
                t.setItem(loadMenuItem(itemId));
                t.setTaskId(taskId);
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return t;
    }
    private MenuItem loadMenuItem(int itemId) {
        // Caricamento voci
        // Non verifichiamo se un MenuItem è già stato creato perché
        // questo può avvenire solo nel contesto del caricamento di un Event
        // e il MenuItem può essere già creato solo se il Event è stato creato;
        // il controllo sul Event avviene già in loadMenus
        Statement st = null;
        String query = "SELECT * FROM MenuItems WHERE MenuItems.id=" +itemId;
        MenuItem it= null;
        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String description = rs.getString("description");
                System.out.println("item desc: "+description);
                int idSec = rs.getInt("section");
                int idIt = rs.getInt("id");
                int idRec = rs.getInt("recipe");
                Recipe rec = this.innerLoadRecipe(idRec);
                 it= new MenuItem(description);
                it.setItemId(idIt);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return it;
    }
    //UPLOAD
    public boolean controllSSExist(int chefId){
        PreparedStatement pst = null;
        String sql = "select * from summary_sheets where summary_sheets.chef_id=?";
        boolean exist=false;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,chefId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                exist=true;
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return exist;
    }
    public boolean controllShiftExist(int stId){
        PreparedStatement pst = null;
        String sql = "select * from rel_st_shift where rel_st_shift.st_id=?";
        boolean exist=false;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,stId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                exist=true;
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return exist;
    }
    public boolean controllStaffExist(int stId){
        PreparedStatement pst = null;
        String sql = "select * from rel_st_staff where rel_st_staff.staff_id=?";
        boolean exist=false;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,stId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                exist=true;
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return exist;
    }
    public boolean controllSTExist(int sId){
        PreparedStatement pst = null;
        String sql = "select * from shift_task where shift_task.summary_sheet_id=?";
        boolean exist=false;

        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1,sId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                exist=true;
            }
            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return exist;
    }
    public void uploadSummarySheet(){
        SummarySheet ss= CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet();
        String sql=null;
        boolean exist= controllSSExist(ss.getChefId());
        int id = -1;
        if(!exist) {
            sql = "INSERT INTO summary_sheets(title,note,chef_id) " +
                    "VALUES(?,?,?)";
        }else{
            sql="update summary_sheets set title=?,note=? where summary_sheets.chef_id=?";
            System.out.println("exist");
            id=ss.getId();
        }
            PreparedStatement pstmt = null;
            try {

                pstmt = this.connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, ss.getTitle());
                pstmt.setString(2, ss.getNote());
                pstmt.setInt(3, ss.getChefId());
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                rs.close();
                pstmt.close();

            } catch (SQLException exc) {
                exc.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                } catch (SQLException exc2) {
                    exc2.printStackTrace();
                }
            }
            System.out.println("uploaded sheet : " + id);
            uploadShiftTask(id);


    }
    public int uploadShiftTask(int summaryShettId){//0 not exist 1 exist
        int id = -1;
        int idForDB;
        String sql = null;
        boolean exist=controllSTExist(summaryShettId);
        if(!exist) {

            sql = "INSERT INTO shift_task(summary_sheet_id,position) " +
                    "VALUES(?,?)";
            System.out.println("not exist st");
        }else{
            sql="update shift_task set summary_sheet_id=?,position=? where shift_task.id=?";
            System.out.println("exist st");
        }
            PreparedStatement pstmt = null;
            Map stMap = CateringAppManager.eventManager.getCurrentEvent().getCurrentSummarySheet().getStList();
            System.out.println("shifts: " + stMap.size());
            List<ShiftTask> stList = new ArrayList<>(stMap.values());
            try {
                for (ShiftTask st : stList
                ) {
                    pstmt = this.connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    pstmt.setInt(1, summaryShettId);
                    pstmt.setInt(2, st.getPosition());
                    if(exist){
                        pstmt.setInt(3, st.getId());
                    }
                    pstmt.executeUpdate();
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    rs.close();
                    pstmt.close();
                    idForDB=(id!=-1)?id:st.getId();
                    uploadTaskST(idForDB, st,exist);
                    uploadShiftST(idForDB, st);
                    uploadStaffST(idForDB, st);
                    System.out.println("update st");
                }

            } catch (SQLException exc) {
                exc.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                } catch (SQLException exc2) {
                    exc2.printStackTrace();
                }
            }

        return id;
    }
    public int uploadShiftST(int stId,ShiftTask st){
        String sql=null;
        boolean exist=controllShiftExist(stId);
        if(!exist){
            sql= "INSERT INTO rel_st_shift(shift_id,st_id) " +
                    "VALUES(?,?)";
        }else{
            sql="update rel_st_shift set shift_id=? where rel_st_shift.st_id=?";
        }

        int id = -1;
        PreparedStatement pstmt = null;
        Map shiftMap= st.getShifts();
        List<Shift> shiftList= new ArrayList<>(shiftMap.values());
        try {
            for (Shift sh:shiftList
                 ) {
                pstmt = this.connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, sh.getShiftId());
                pstmt.setInt(2, stId);
                pstmt.executeUpdate();
                ResultSet rs=pstmt.getGeneratedKeys();
                if(rs.next()){
                    id=rs.getInt(1);
                }
                rs.close();
                pstmt.close();
                System.out.println("shift: "+sh.getShiftId()+" "+stId+" uploaded");
            }



        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return id;
    }
    public int uploadStaffST(int stId,ShiftTask st){
        String sql=null;
        boolean exist=controllStaffExist(stId);
        if(!exist){
            sql = "INSERT INTO rel_st_staff(staff_id,st_id) " +
                    "VALUES(?,?)";
        }else{
            sql = "update rel_st_staff set staff_id=? where rel_st_staff.st_id=?";
        }

        int id = -1;
        PreparedStatement pstmt = null;
        Map staffMap = st.getChoosenStaff();
        List<Staff>staffList= new ArrayList<>(staffMap.values());
        try {
            for (Staff staff:staffList
                 ) {
                pstmt = this.connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, staff.getStaffId());
                pstmt.setInt(2, stId);
                pstmt.executeUpdate();
                ResultSet rs=pstmt.getGeneratedKeys();
                if(rs.next()){
                    id=rs.getInt(1);
                }
                rs.close();
                pstmt.close();
            }



        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return id;
    }
    public int uploadTaskST(int stId,ShiftTask st,boolean exist){
        String sql = "INSERT INTO rel_st_task(task_id,st_id) " +
                "VALUES(?,?)";
        int id = -1;
        PreparedStatement pstmt = null;
        Task t= st.getTask();
        int taskId=uploadTask(t,exist);
        if(!exist){
        try {

            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, taskId);
            pstmt.setInt(2, stId);
            pstmt.executeUpdate();
            ResultSet rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            pstmt.close();


        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        }
        return id;
    }
    public int uploadTask(Task task,boolean exist){
        String sql=null;
        if(!exist) {
            sql = "INSERT INTO tasks(item_id,estimated_time,quantity,status,title) " +
                    "VALUES(?,?,?,?,?)";
        }else{
            sql="update tasks set estimated_time=?,quantity=?,title=? where tasks.id=?";
        }
        int id = -1;
        PreparedStatement pstmt = null;
        try {

            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            if(!exist) {
                pstmt.setInt(1, task.getItem().getItemId());
                pstmt.setInt(2, task.getEstimatedTime());
                pstmt.setInt(3, task.getQuantity());
                pstmt.setInt(4, (task.getStatusBool()) ? 1 : 0);
                pstmt.setString(5, task.getTitle());
            }else{
                pstmt.setInt(1, task.getEstimatedTime());
                pstmt.setInt(2, task.getQuantity());
                pstmt.setString(3, task.getTitle());
                pstmt.setInt(4, task.getTaskId());
            }
            pstmt.executeUpdate();
            ResultSet rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            pstmt.close();


        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return id;
    }
/*
    private int writeNewMenu(Event m) {

        String sql = "INSERT INTO Menus(title, menuowner, published, fingerFood, " +
                "cookRequired, hotDishes, kitchenRequired, buffet) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        int id = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, m.getTitle());
            pstmt.setInt(2, this.userObjects.get(m.getOwner()));
            pstmt.setBoolean(3, m.isPublished());
            pstmt.setBoolean(4, m.isFingerFood());
            pstmt.setBoolean(5, m.isCookRequired());
            pstmt.setBoolean(6, m.isHotDishes());
            pstmt.setBoolean(7, m.isKitchenRequired());
            pstmt.setBoolean(8, m.isBuffet());

            int r = pstmt.executeUpdate();

            if (r == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return id;
    }

    private void writeMenuChanges(Event m) {
        int mid = menuObjects.get(m);
        int uid = userObjects.get(m.getOwner());
        String sql = "UPDATE Menus SET menuowner=?, published=?, fingerFood=?, cookRequired=?, hotDishes=?, "
                + "kitchenRequired=?, buffet=?, title=? WHERE id=" + mid;
        PreparedStatement pstmt = null;

        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, uid);
            pstmt.setBoolean(2, m.isPublished());
            pstmt.setBoolean(3, m.isFingerFood());
            pstmt.setBoolean(4, m.isCookRequired());
            pstmt.setBoolean(5, m.isHotDishes());
            pstmt.setBoolean(6, m.isKitchenRequired());
            pstmt.setBoolean(7, m.isBuffet());
            pstmt.setString(8, m.getTitle());

            int r = pstmt.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
    }

    private void removeMenu(Event m) {
        int mId = menuObjects.get(m);
        String sqlItems = "DELETE FROM MenuItems WHERE menu=?";
        String sqlSections = "DELETE FROM Sections WHERE menu=?";
        String sqlMenu = "DELETE FROM Menus WHERE id=?";
        PreparedStatement pstItems = null;
        PreparedStatement pstSections = null;
        PreparedStatement pstMenu = null;
        try {
            connection.setAutoCommit(false);
            pstItems = connection.prepareStatement(sqlItems);
            pstSections = connection.prepareStatement(sqlSections);
            pstMenu = connection.prepareStatement(sqlMenu);
            pstItems.setInt(1, mId);
            pstSections.setInt(1, mId);
            pstMenu.setInt(1, mId);
            pstItems.executeUpdate();
            pstSections.executeUpdate();
            pstMenu.executeUpdate();
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                if (pstItems != null) pstItems.close();
                if (pstSections != null) pstSections.close();
                if (pstMenu != null) pstMenu.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }

        }
    }


    private int writeNewSection(int menuId, int position, Section sec) {

        String sql = "INSERT INTO Sections(menu, name, position) VALUES(?,?,?)";
        int id = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, menuId);
            pstmt.setString(2, sec.getName());
            pstmt.setInt(3, position);

            int r = pstmt.executeUpdate();

            if (r == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }

        return id;
    }

    private void writeSectionChanges(int menuId, int position, Section sec) {
        int sId = sectionObjects.get(sec);
        String sql = "UPDATE Sections SET menu=?, name=?, position=? WHERE id=" + sId;
        PreparedStatement pstmt = null;
        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, menuId);
            pstmt.setString(2, sec.getName());
            pstmt.setInt(3, position);

            pstmt.executeUpdate();

        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
    }

    private void removeSection(Section s) {
        int sId = sectionObjects.get(s);
        String sqlItems = "DELETE FROM MenuItems WHERE section=?";
        String sqlSection = "DELETE FROM Sections WHERE id=?";
        PreparedStatement pstItems = null;
        PreparedStatement pstSection = null;
        try {
            connection.setAutoCommit(false);
            pstItems = connection.prepareStatement(sqlItems);
            pstSection = connection.prepareStatement(sqlSection);
            pstItems.setInt(1, sId);
            pstSection.setInt(1, sId);
            pstItems.executeUpdate();
            pstSection.executeUpdate();
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                if (pstItems != null) pstItems.close();
                if (pstSection != null) pstSection.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }

        }
    }

    private int writeNewItem(int menuId, int secId, int position, MenuItem item) {

        String sql = "INSERT INTO MenuItems(menu, section, description, recipe, position) " +
                "VALUES(?,?,?,?,?)";
        int id = -1;
        PreparedStatement pstmt = null;

        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, menuId);
            pstmt.setInt(2, secId);
            pstmt.setString(3, item.getDescription());
            pstmt.setInt(4, recipeObjects.get(item.getRecipe()));
            pstmt.setInt(5, position);

            int r = pstmt.executeUpdate();

            if (r == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }

        return id;
    }

    private void writeItemChanges(int menuId, int secId, int position, MenuItem item) {
        int itId = itemObjects.get(item);
        String sql = "UPDATE MenuItems SET menu=?, section=?, description=?, recipe=?, position=? WHERE " +
                "id=" + itId;
        PreparedStatement pstmt = null;

        try {
            pstmt = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, menuId);
            pstmt.setInt(2, secId);
            pstmt.setString(3, item.getDescription());
            pstmt.setInt(4, recipeObjects.get(item.getRecipe()));
            pstmt.setInt(5, position);

            pstmt.executeUpdate();

        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
    }

    private void removeItem(MenuItem it) {
        int iId = itemObjects.get(it);
        String sqlItems = "DELETE FROM MenuItems WHERE id=?";
        PreparedStatement pstItems = null;
        try {
            pstItems = connection.prepareStatement(sqlItems);
            pstItems.setInt(1, iId);
            pstItems.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();

        } finally {
            try {
                if (pstItems != null) pstItems.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }

        }
    }
















*/

   /* public List<Menu> loadMenus() {
        List<Menu> ret = new ArrayList<>();
        Statement st = null;
        String query = "SELECT * FROM Menus";

        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");

                // Verifica se per caso l'ha già caricato
                Menu m = this.idToMenuObject.get(id);
                if (m == null) {

                    String title = rs.getString("title");

                    int ownerid = rs.getInt("menuowner");
                    User owner = this.innerLoadUser(ownerid);

                    m = new Menu(owner, title);
                    m.setPublished(rs.getBoolean("published"));
                    m.setBuffet(rs.getBoolean("buffet"));
                    m.setCookRequired(rs.getBoolean("cookRequired"));
                    m.setFingerFood(rs.getBoolean("fingerFood"));
                    m.setHotDishes(rs.getBoolean("hotDishes"));
                    m.setKitchenRequired(rs.getBoolean("kitchenRequired"));

                    // per sapere se il menu è in uso consulto la tabella degli eventi
                    // NdR: un menu è in uso anche se l'evento che lo usa è concluso o annullato
                    Statement st2 = this.connection.createStatement();
                    String query2 = "SELECT Events.id FROM Events JOIN Menus M on Events.menu = M.id WHERE M.id=" + id;
                    ResultSet rs2 = st2.executeQuery(query2);
                    m.setInUse(rs2.next());
                    st2.close();
                    loadMenuSections(id, m);
                    loadMenuItems(id, m);


                    ret.add(m);
                    this.menuObjects.put(m, id);
                    this.idToMenuObject.put(id, m);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return ret;
    }*/
   private Recipe innerLoadRecipe(int idRec) {
       // verifico se l'ho già caricato in precedenza
       Recipe rec = this.idToRecipeObject.get(idRec);
       if (rec != null) return rec;
       Statement st = null;

       String query = "SELECT * FROM Recipes WHERE Recipes.id = " + idRec;
       try {
           st = this.connection.createStatement();
           ResultSet rs = st.executeQuery(query);
           if (rs.next()) {
               String name = rs.getString("name");
               char type = rs.getString("type").charAt(0);
               rec = createRecipeWithType(name, type);
               this.recipeObjects.put(rec, idRec);
               this.idToRecipeObject.put(idRec, rec);
           }
       } catch (SQLException exc) {
           exc.printStackTrace();
       } finally {
           try {
               if (st != null) st.close();
           } catch (SQLException exc2) {
               exc2.printStackTrace();
           }
       }

       return rec;
   }
    private User innerLoadUser(int userId) {
        // verifico se l'ho già caricato in precedenza
        User u = this.idToUserObject.get(userId);
        if (u != null) return u;

        Statement st = null;
        String query = "SELECT Users.id, Users.name, UserRoles.role FROM Users LEFT JOIN UserRoles on Users.id = " +
                "UserRoles.user where Users.id=" + userId;
        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (u == null) {
                    u = new User(rs.getString("name"));
                    this.userObjects.put(u, userId);
                    this.idToUserObject.put(userId, u);
                }
                addUserRole(u, rs);
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }

        return u;
    }
    private void addUserRole(User u, ResultSet rs) throws SQLException {
        char roleName = rs.getString("role").charAt(0);
        switch (roleName) {
            case 'c':
                u.addRole(User.Role.Cuoco);
                break;
            case 'h':
                u.addRole(User.Role.Chef);
                break;
            case 'o':
                u.addRole(User.Role.Organizzatore);
                break;
            case 's':
                u.addRole(User.Role.Servizio);
                break;
        }
    }
    private void loadMenuSections(int id, Menu m) {
        // Caricamento sezioni
        // Non verifichiamo se una Section è già stata creata perché
        // questo può avvenire solo nel contesto del caricamento di un Event
        // e la Section può essere già creata solo se il Event è stato creato;
        // il controllo sul Event avviene già in loadMenus
        Statement st = null;
        String query = "SELECT Sections.* FROM Sections WHERE Sections.menu=" + id + " ORDER BY Sections.position";

        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int idSec = rs.getInt("id");

                Section sec = m.addSection(name);
                this.sectionObjects.put(sec, idSec);
                this.idToSectionObject.put(idSec, sec);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }

    }
    private Recipe createRecipeWithType(String name, char type) {
        switch (type) {
            case 'r':
                return new Recipe(name, Recipe.Type.Dish);
            case 'p':
                return new Recipe(name, Recipe.Type.Preparation);

        }
        return null;
    }
  /*  public List<Recipe> loadRecipes() {
        Statement st = null;
        String query = "SELECT * FROM Recipes";
        List<Recipe> ret = new ArrayList<>();

        try {
            st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                char type = rs.getString("type").charAt(0);
                int id = rs.getInt("id");

                // Verifica se per caso l'ha già caricata
                Recipe rec = this.idToRecipeObject.get(id);

                if (rec == null) {
                    rec = createRecipeWithType(name, type);

                    if (rec != null) {
                        ret.add(rec);
                        this.recipeObjects.put(rec, id);
                        this.idToRecipeObject.put(id, rec);
                    }
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException exc2) {
                exc2.printStackTrace();
            }
        }
        return ret;
    }*/
        }
