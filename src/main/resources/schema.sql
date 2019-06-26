 DROP TABLE Pages IF EXISTS;
	 CREATE TABLE Pages
	 (
	    pageId IDENTITY NOT NULL,
	    name VARCHAR(255) NOT NULL,
	    textDesc VARCHAR(255) NOT NULL,
  		detailPage VARCHAR(255) NULL
	 );
