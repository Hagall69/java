package menu;

enum MenuContent {
    ALL {
        public String toString(String str){
            return "Все " + str;
        }
    },
    ADD {
        public String toString(String str){
            return "Добавить " + str;
        }
    },
    FIND {
        public String toString(String str){
            return "Найти " + str;
        }
    },
    REPLACE {
        public String toString(String str){
            return "Изменить " + str;
        }
    },
    DELETE {
        public String toString(String str){
            return "Удалить " + str;
        }
    },
    PREVIOUS_MENU {
        public String toString(String str){
            return "Предыдущее меню";
        }
    };
    public abstract String toString(String str);
}