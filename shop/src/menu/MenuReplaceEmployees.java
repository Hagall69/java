package menu;

enum MenuReplaceEmployees {
    FULL_NAME{
        public String toString(){ return "ФИО";}
        public String toColumn(){ return "name";}
    },
    POSITION{
        public String toString(){ return "Должность";}
        public String toColumn(){ return "position";}
    },
    SALARY{
        public String toString(){ return "Зарплата";}
        public String toColumn(){ return "salary";}
    },
    CANCEL{
        public String toString(){ return "Отмена";}
        public String toColumn(){ return "Отмена";}
    };
    public abstract String toString();
    public abstract String toColumn();
}
