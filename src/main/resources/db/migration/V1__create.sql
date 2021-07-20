create table ProductCategory (
    Id int auto_increment primary key,
    Name varchar2(20),
    Description varchar2(50)
);

create table Supplier (
    Id int auto_increment primary key,
    Name varchar2(20)
);

create table Product (
    Id int auto_increment primary key,
    Name varchar2(30),
    Description varchar2(50),
    Price decimal,
    Weight double,
    Category int,
    Supplier int,
    ImageUrl varchar(50)
);

create table Location (
    Id int auto_increment primary key,
    Name varchar2(20),
    `Address.Country` varchar2(30),
    `Address.City` varchar2(20),
    `Address.County` varchar2(10),
    `Address.StreetAddress` varchar2(30)
);

create table Stock (
    Product int,
    Location int,
    Quantity int,
    constraint PK_Stock primary key (Product, Location)
);

create table Customer (
    Id int auto_increment primary key,
    FirstName varchar2(20),
    LastName varchar2(10),
    Username varchar2(15),
    Password varchar2(30),
    EmailAddress varchar2(30)
);

create table Revenue (
    Id int auto_increment primary key,
    Location int,
    Date date,
    Sum decimal
);

create table `Order` (
    Id int auto_increment primary key,
    ShippedFrom int,
    Customer int,
    CreatedAt timestamp,
    `Address.Country` varchar2(30),
    `Address.City` varchar2(20),
    `Address.County` varchar2(10),
    `Address.StreetAddress` varchar2(30)
);

create table OrderDetail (
    `Order` int,
    Product int,
    Quality int,
    constraint PK_OrderDetail primary key (`Order`, Product)
)