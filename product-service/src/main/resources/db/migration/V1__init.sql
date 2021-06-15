BEGIN TRANSACTION ;

CREATE TABLE products
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255)   NOT NULL UNIQUE,
    price      NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO products (title, price) VALUES
('Adobe Photoshop Elements 2021', 150),
('CLIP STUDIO PAINT PRO', 60),
('CorelDRAW Graphics Suite 2020', 340),
('Corel PaintShop Pro 2021 Ultimate', 70),
('Cyberlink PowerDirector 19 Ultimate', 140),
('Corel VideoStudio Ultimate 2020', 50),
('Webroot Antivirus Software 2021', 20),
('Trend Micro Maximum Security 2021', 37),
('McAfee LiveSafe Ultimate Protection for Unlimited Devices', 38),
('Malwarebytes Premium 4.0 Latest Version', 100),
('Norton Small Business - 5 Device', 100),
('AVG Technologies AVG Internet Security 2020', 90),
('Windows Server 2019 Standard', 250),
('Windows Server 2016 Standard', 200),
('Window 10 Home', 105),
('OfficeSuite Home & Business 2021', 100),
('Corel WordPerfect Office 2020 Home & Student', 60),
('Window 7 Professional', 70),
('Microsoft Project Professional 2019', 460),
('Nuance Dragon NaturallySpeaking Premium 13', 204);

COMMIT;