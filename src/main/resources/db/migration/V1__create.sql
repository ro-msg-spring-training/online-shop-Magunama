create table Product_Category (
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
    Image_Url varchar(50)
);

create table Location (
    Id int auto_increment primary key,
    Name varchar2(20),
    Address_Country varchar2(30),
    Address_City varchar2(20),
    Address_County varchar2(10),
    Address_Street_Address varchar2(30)
);

create table Stock (
    Product int,
    Location int,
    Quantity int,
    constraint PK_Stock primary key (Product, Location)
);

create table Customer (
    Id int auto_increment primary key,
    First_Name varchar2(20),
    Last_Name varchar2(10),
    Username varchar2(15),
    Password varchar2(30),
    Email_Address varchar2(30)
);

create table Revenue (
    Id int auto_increment primary key,
    Location int,
    Date date,
    Sum decimal
);

create table OrderT (
    Id int auto_increment primary key,
    Shipped_From int,
    Customer int,
    Created_At timestamp,
    Address_Country varchar2(30),
    Address_City varchar2(20),
    Address_County varchar2(10),
    Address_Street_Address varchar2(30)
);

create table Order_Detail (
    `Order` int,
    Product int,
    Quantity int,
    constraint PK_OrderDetail primary key (`Order`, Product)
)