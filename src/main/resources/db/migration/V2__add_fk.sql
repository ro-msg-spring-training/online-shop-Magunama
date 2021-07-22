alter table Product
    add foreign key (Category)
    references Product_Category(Id);

alter table Product
    add foreign key (Supplier)
    references Supplier(Id);

alter table Stock
    add foreign key (Product)
    references Product(Id);

alter table Stock
    add foreign key (Location)
    references Location(Id);

alter table Order_Detail
    add foreign key (OrderC)
    references OrderT(Id);

alter table Order_Detail
    add foreign key (Product)
    references Product(Id);

alter table OrderT
    add foreign key (Shipped_From)
    references Location(Id);

alter table OrderT
    add foreign key (Customer)
    references Customer(Id);

alter table Revenue
    add foreign key (Location)
    references Location(Id);