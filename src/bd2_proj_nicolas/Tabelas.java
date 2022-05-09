package bd2_proj_nicolas;

public enum Tabelas {
    
    categorias("Categories", 4, new String[] {"CategoryID", "CategoryName", "Description", "Picture"}), 
    democli("CustomerCustomerDemo", 2, new String[] {"CustomerID", "CustomerTypeID"}),
    demogcli("CustomerDemographics", 2, new String[] {"CustomerTypeID", "CustomerDesc"}),
    clientes("Customers", 11, new String[] {"CustomerID", "CompanyName", "ContactName", "ContactTitle", "Address", "City", "Region", "PostalCode", "Country", "Phone", "Fax"}), 
    empregados("Employees", 18, new String[] {"EmployeeID", "LastName", "FirstName", "Title", "TitleOfCourtesy", "BirthDate", "HireDate", "Address", "City", "Region", "PostalCode", "Country", "HomePhone", "Extension", "Photo", "Notes", "ReportsTo", "PhotoPath"}),
    terriemprega("EmployeeTerritories", 2, new String[] {"EmployeeID", "TerritoryID"}),
    detalhepedi("Order Details", 5, new String[] {"OrderID", "ProductID", "UnitPrice", "Quantity", "Discount"}),
    pedidos("Orders", 14, new String[] {"OrderID", "CustomerID", "EmployeeID", "OrderDate", "RequiredDate", "ShippedDate", "ShipVia", "Freight", "ShipName", "ShipAddress", "ShipCity", "ShipRegion", "ShipPostalCode", "ShipCountry"}),
    produtos("Products", 10 , new String[] {"ProductId", "ProductName", "SupplierID", "CategoryID", "QuantityPerUnit", "UnitPrice", "UnitsInStock", "UnitsOnOrder", "ReorderLevel", "Discontinued"}),
    regiao("Region", 2, new String[] {"RegionID", "RegionDescription"}),
    entregadores("Shippers", 3 , new String[] {"ShipperID", "CompanyName", "Phone"}),
    fornecedores("Suppliers", 12 , new String[] {"SupplierID", "CompanyName", "ContactName", "ContactTitle", "Address", "City", "Region", "PostalCode", "Country", "Phone", "Fax", "Homepage"}),
    territorios("Territories", 3 , new String[] {"TerritoryID", "TerritoryDescription", "RegionID"});
    
    private static int IDcount;
    public final int VALUE_ID;
    private String nome;
    private int numCol;
    private String[] Attributes;
    
    Tabelas(String nome, int numCol, String[] att){
        this.VALUE_ID = getID();
        this.nome = nome;
        this.numCol = numCol;
        this.Attributes = att;
    }
    
    private int getID(){
        return IDcount++;
    }  

    public static Tabelas valueOfName(String name){
        Tabelas[] tabs = Tabelas.values();
        for(int i = 0; i < tabs.length; i++){
            if(tabs[i].getNome().compareTo(name) == 0){
                return tabs[i];
            }
        }
        return null;
    }
    
    public static Tabelas valueOf(int ID){
        Tabelas[] tabs = Tabelas.values();
        return tabs[ID];
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getNumCol(){
        return this.numCol;
    }
    
    public String[] getAttributes(){
        return this.Attributes;
    }
}
