package menu;

enum MenuFindEmployees {
    ID {
        public String toString(){ return "ID";}
        public String toColumn(){ return "id";}
    },
    FULL_NAME {
        public String toString(){ return "ФИО";}
        public String toColumn(){ return "name";}
    },
    POSITION {
        public String toString(){ return "Должность";}
        public String toColumn(){ return "position";}
    },
    CANCEL {
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}