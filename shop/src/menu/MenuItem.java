package menu;

enum MenuItem {
    CLIENTS {
        public String toString() {return "Клиенты";}
        public String toString(String str) {
            String name = "Клиент";
            return name + str;
        }
    },
    EMPLOYEES {
        public String toString() {return "Сотрудники";}
        public String toString(String str) {
            String name = "Сотрудник";
            return name + str;
        }
    },
    PRODUCTS {
        public String toString() {return "Товары";}
        public String toString(String str) {
            String name = "Товар";
            return name + str;
        }
    },
    EXIT {
        public String toString() { return "Выход";}
        public String toString(String str) { return "Выход";}
    };
    public abstract String toString();
    public abstract String toString(String str);
}
