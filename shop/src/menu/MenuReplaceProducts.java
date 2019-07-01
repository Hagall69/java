package menu;

enum MenuReplaceProducts {
    NAME{
        public String toString(){ return "Наименование";}
        public String toColumn(){ return "name";}
    },
    BARCODE{
        public String toString(){ return "Штрихкод";}
        public String toColumn(){ return "barcode";}
    },
    PRICE{
        public String toString(){ return "Цена";}
        public String toColumn(){ return "price";}
    },
    DISCOUNT{
        public String toString(){ return "Скидка";}
        public String toColumn(){ return "discount";}
    },
    CANCEL{
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}

