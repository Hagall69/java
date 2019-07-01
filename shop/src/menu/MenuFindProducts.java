package menu;

enum MenuFindProducts {
    ID {
        public String toString(){ return "ID";}
        public String toColumn(){ return "id";}
    },
    NAME {
        public String toString(){ return "Наименование";}
        public String toColumn(){ return "name";}
    },
    BARCODE {
        public String toString(){ return "Штрихкод";}
        public String toColumn(){ return "barcode";}
    },
    CANCEL {
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}
