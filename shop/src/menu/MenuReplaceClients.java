package menu;

public enum MenuReplaceClients {
    FULL_NAME{
        public String toString(){ return "ФИО";}
        public String toColumn(){ return "name";}
    },
    POINTS{
        public String toString(){ return "Баллы";}
        public String toColumn(){ return "points";}
    },
    CANCEL{
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}
