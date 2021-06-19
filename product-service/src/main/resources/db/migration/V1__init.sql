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
('Webroot Antivirus Software 2021', 20);

COMMIT;