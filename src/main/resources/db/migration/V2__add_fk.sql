alter table Product
    add foreign key (Category)
    references ProductCategory(Id);

alter table Product
    add foreign key (Supplier)
    references Supplier(Id);

alter table Stock
    add foreign key (Product)
    references Product(Id);

alter table Stock
    add foreign key (Location)
    references Location(Id);

alter table OrderDetail
    add foreign key (`Order`)
    references `Order`(Id);

alter table OrderDetail
    add foreign key (Product)
    references Product(Id);

alter table `Order`
    add foreign key (ShippedFrom)
    references Location(Id);

alter table `Order`
    add foreign key (Customer)
    references Customer(Id);

alter table Revenue
    add foreign key (Location)
    references Location(Id);