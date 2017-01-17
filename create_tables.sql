create table if not exists Users(
	UserID int primary key auto_increment,
	Username char(30) not null,
	Nickname char(30) not null,
	Firstname char(30) not null,
	Lastname char(30) not null,
	Password char(30) not null check(Password >= 8 and Password <= 30),
	EmailAddress char(100) not null,
	YearOfBirth date not null,
	Address char(50) not null,
	CreditCardNumber char(20) not null,
	IsAuthenticated boolean default false,
	IsBanned boolean default false
) ENGINE=InnoDB;

create table if not exists Venues(
	VenueID int primary key auto_increment,
	VenueName varchar(100)
) ENGINE=InnoDB;

create table if not exists Items(
	ItemID int primary key auto_increment,
	PublishType char(50),
	Title char(200),
	yearPublished int,
	Booktitle char(50),
	Pages char(10),
	Journal char(50),
	Vol char(5),
	Num char(5),
	Month int,
	URL char(50),
	EE char(50),
	cdrom char(50),
	cite char(50),
	Publisher char(50),
	Note char(50),
	CrossRef char(50),
	ISBN char(20),
	Series char(20),
	School char(30),
	Chapter char(30),
	Price real default 10,
	IsPaused boolean default false,
	UserID int,
	foreign key(UserID) references Users(UserID) on delete cascade
) ENGINE=InnoDB;

create table if not exists Pictures(
	PictureID int primary key auto_increment,
	PictureName varchar(100),
	PictureSource longblob default null,
	ItemID int,
	foreign key(ItemID) references Items(ItemID) on delete cascade
) ENGINE=InnoDB;

create table if not exists Editors(
	EditorID int primary key auto_increment,
	EditorName varchar(100)
) ENGINE=InnoDB;

create table if not exists EditedBy(
	EditorID int,
	ItemID int,
	foreign key(EditorID) references Editors(EditorID) on delete cascade,
	foreign key(ItemID) references Items(ItemID) on delete cascade
) ENGINE=InnoDB;

create table if not exists Authors(
	AuthorID int primary key auto_increment,
	AuthorName varchar(100)
) ENGINE=InnoDB;

create table if not exists AuthoredBy(
	AuthorID int,
	ItemID int,
	foreign key(AuthorID) references Authors(AuthorID) on delete cascade,
	foreign key(ItemID) references Items(ItemID) on delete cascade
) ENGINE=InnoDB;

create table if not exists Sessions(
	UserID int,
	SessionKey char(32) unique not null,
	CreatedOn datetime not null,
	foreign key(UserID) references Users(UserID) on delete cascade
) ENGINE=InnoDB;

create table if not exists Privileges(
	PrivilegeID int primary key auto_increment,
	IsAdmin boolean default false,
	UserID int,
	foreign key(UserID) references Users(UserID) on delete cascade
) ENGINE=InnoDB;

create table if not exists States(
	StateID int primary key auto_increment,
	State varchar(20) default null
) ENGINE=InnoDB;

create table if not exists OrderedItems(
	OrderedItemID int primary key auto_increment,
	AddedTime datetime,
	ChangedTime datetime,
	UserID int,
	ItemID int,
	StateID int,
	foreign key(UserID) references Users(UserID) on delete cascade,
	foreign key(ItemID) references Items(ItemID) on delete cascade,
	foreign key(StateID) references States(StateID) on delete cascade
) ENGINE=InnoDB;

create table if not exists CartItems(
	CartItemID int primary key auto_increment,
	Quantity int default 1 not null,
	UserID int,
	ItemID int,
	foreign key(UserID) references Users(UserID) on delete cascade,
	foreign key(ItemID) references Items(ItemID) on delete cascade
) ENGINE=InnoDB;

create table if not exists PageVisited(
	PageVisitedID int primary key auto_increment,
	PageLink varchar(50) not null,
	StartTime datetime not null,
	EndTime datetime not null,
	UserID int,
	foreign key(UserID) references Users(UserID) on delete cascade
) ENGINE=InnoDB;

create table if not exists EntityStore(
	Subject varchar(20),
	Predicate varchar(20),
	Object varchar(200)
) ENGINE=InnoDB;

create table if not exists GraphStore(
	Subject varchar(20),
	Predicate int primary key auto_increment,
	Object varchar(20)
) ENGINE=InnoDB;
