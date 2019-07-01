package menu;

public enum Tables {
    CLIENTS{
        public String toString(){ return "clients";}
        },
    EMPLOYEES{
        public String toString(){ return "employees";}
    },
    PRODUCTS{
        public String toString(){ return "products";}
    };
    public abstract String toString();
}
