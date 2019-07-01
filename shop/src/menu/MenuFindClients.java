package menu;

enum  MenuFindClients {
    ID {
        public String toString(){ return "ID";}
        public String toColumn(){ return "id";}
    },
    FULL_NAME {
        public String toString(){ return "ФИО";}
        public String toColumn(){ return "name";}
    },
    CANCEL {
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}
