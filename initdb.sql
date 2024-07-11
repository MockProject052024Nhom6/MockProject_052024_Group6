create database auction_management
use auction_management

INSERT INTO tbl_warehouse(warehouse_name, asset_placement,address_warehouse,status)
VALUES
    ('Main Warehouse', 'A1', '123 Main St, City A', 1),
    ('Secondary Warehouse', 'B1', '456 Elm St, City B', 1),
    ('East Warehouse', 'C1', '789 Pine St, City C', 1),
    ('West Warehouse', 'D1', '101 Maple St, City D', 1),
    ('North Warehouse', 'E1', '202 Oak St, City E', 1),
    ('South Warehouse', 'F1', '303 Birch St, City F', 1),
    ('Central Warehouse', 'G1', '404 Cedar St, City G', 1),
    ('Coastal Warehouse', 'H1', '505 Spruce St, City H', 1),
    ('Inland Warehouse', 'I1', '606 Willow St, City I', 1),
    ('Mountain Warehouse', 'J1', '707 Aspen St, City J', 1);

INSERT INTO tbl_category_asset(name)
VALUES('Real Estate'),
      ('Antiques'),
      ('Fine Art'),
      ('Jewelry'),
      ('Automobiles'),
      ('Collectibles'),
      ('Furniture'),
      ('Electronics'),
      ('Sports Memorabilia'),
      ('Musical Instruments');

INSERT INTO tbl_role (role_name, description)
VALUES
    ('Admin', 'System administrator, manages the entire website and has full access to all features and data.'),
    ('Auctioneer', 'Conducts auctions, responsible for managing and running auction sessions.'),
    ('Bidder', 'Participant in the auction, can place bids on auctioned products.'),
    ('Seller', 'Vendor, can post and manage their products on the auction website.'),
    ('Appraiser', 'Evaluates the value of assets, responsible for assessing and determining the value of auctioned products.'),
    ('Customer Support', 'Handles customer issues and inquiries.'),
    ('Financial Officer', 'Manages financial transactions and payments.'),
    ('Marketing Specialist', 'Responsible for marketing campaigns and advertising on the website.'),
    ('Warehouse Manager', 'Oversees and manages warehouse operations.'),
    ('User', 'System users.'),
    ('Content Manager', 'Responsible for creating and managing content on the website.');

INSERT INTO tbl_user (first_name, last_name, username, date_of_birth, phone, another_phone,
                      email, user_status, gender, password, country, city, state, credibility, identification_card, status)
VALUES
    ('John', 'Doe', 'johndoe', '1985-04-23', '555-1234', '555-5678', 'johndoe@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'New York', 'NY', 50, '123456789', 1),
    ('Jane', 'Smith', 'janesmith', '1990-06-15', '555-2345', '555-6789', 'janesmith@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Los Angeles', 'CA', 40, '987654321', 1),
    ('Michael', 'Johnson', 'michaelj', '1988-09-12', '555-3456', '555-7890', 'michaelj@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Chicago', 'IL', 30, '112233445', 1),
    ('Emily', 'Davis', 'emilyd', '1995-03-10', '555-4567', '555-8901', 'emilyd@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Houston', 'TX', 40, '998877665', 1),
    ('David', 'Miller', 'davidm', '1982-01-18', '555-5678', '555-9012', 'davidm@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Phoenix', 'AZ', 50, '776655443', 1),
    ('Sophia', 'Wilson', 'sophiaw', '1993-07-21', '555-6789', '555-0123', 'sophiaw@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Philadelphia', 'PA', 30, '334455667', 1),
    ('James', 'Brown', 'jamesb', '1987-11-30', '555-7890', '555-1254', 'jamesb@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'San Antonio', 'TX', 40, '223344556', 1),
    ('Olivia', 'Jones', 'oliviaj', '1992-02-27', '555-8901', '555-2345', 'oliviaj@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'San Diego', 'CA', 50, '778899001', 1),
    ('Benjamin', 'Garcia', 'benjaming', '1984-05-16', '555-9012', '555-3456', 'benjaming@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Dallas', 'TX', 30, '990011223', 1),
    ('Isabella', 'Martinez', 'isabellam', '1991-12-25', '555-0123', '555-4567', 'isabellam@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'San Jose', 'CA', 40, '223344667', 1),
    ('Alexander', 'Rodriguez', 'alexanderr', '1989-08-14', '555-1254', '555-5678', 'alexanderr@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Austin', 'TX', 50, '334455778', 1),
    ('Mia', 'Hernandez', 'miah', '1994-11-03', '555-2346', '555-6780', 'miah@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Jacksonville', 'FL', 30, '445566889', 1),
    ('Ethan', 'Martinez', 'ethanm', '1986-03-22', '555-3457', '555-7891', 'ethanm@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Fort Worth', 'TX', 40, '556677990', 1),
    ('Ava', 'Lopez', 'aval', '1995-09-01', '555-4568', '555-8902', 'aval@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Columbus', 'OH', 50, '667788001', 1),
    ('William', 'Gonzalez', 'williamg', '1983-07-19', '555-5679', '555-9013', 'williamg@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Charlotte', 'NC', 30, '778899112', 1),
    ('Harper', 'Perez', 'harperp', '1990-04-08', '555-6790', '555-0124', 'harperp@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Indianapolis', 'IN', 40, '889900223', 1),
    ('Daniel', 'Thompson', 'danielt', '1987-06-26', '555-7901', '555-1235', 'danielt@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'San Francisco', 'CA', 50, '990011334', 1),
    ('Chloe', 'Anderson', 'chloea', '1992-11-15', '555-8012', '555-2346', 'chloea@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Seattle', 'WA', 30, '001122445', 1),
    ('Matthew', 'Taylor', 'matthewt', '1984-10-04', '555-9123', '555-3457', 'matthewt@example.com', 'ACTIVE', 'Male', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Denver', 'CO', 40, '112233556', 1),
    ('Ella', 'Thomas', 'ellat', '1991-05-23', '555-0124', '555-4568', 'ellat@example.com', 'ACTIVE', 'Female', '$2a$12$33Xg.eg1euNynd4H53xow.UUQ7fldj98oTlW.4B/QgSEusRnO9G12', 'USA', 'Washington', 'DC', 50, '223344667', 1);

INSERT INTO tbl_user_has_role(id_role, id_user)
VALUES
    (1, 1),   -- John Doe là Admin
    (4, 2),   -- Jane Smith là Seller
    (3, 3),   -- Michael Johnson là Bidder
    (4, 4),   -- Emily Davis là Seller
    (3, 5),   -- David Miller là Bidder
    (5, 6),   -- Sophia Wilson là Appraiser
    (4, 7),   -- James Brown là Seller
    (10, 8),
    (2, 9),   -- Benjamin Garcia là Auctioneer
    (4, 10),  -- Isabella Martinez là Seller
    (10, 11),
    (10, 12),
    (4, 13),  -- Ethan Martinez là Seller
    (3, 14),  -- Ava Lopez là Bidder
    (5, 15),  -- William Gonzalez là Appraiser
    (10, 16),
    (2, 17),  -- Daniel Thompson là Auctioneer
    (4, 18),  -- Chloe Anderson là Seller
    (10, 19),  -- Matthew Taylor là Financial Officer
    (3, 20);  -- Ella Thomas là Bidder

INSERT INTO tbl_notification (content, title, status)
VALUES
    ('The auction for item #1234 is now live.', 'Auction Live', 1),
    ('Your bid on item #5678 has been placed successfully.', 'Bid Placed', 1),
    ('New items have been added to the auction catalog.', 'New Items Added', 1),
    ('The auction for item #9101 has ended. Thank you for participating.', 'Auction Ended', 1),
    ('Your account has been successfully updated.', 'Account Update', 1),
    ('You have a new message from customer support.', 'New Message', 1),
    ('Reminder: The auction for item #1123 starts tomorrow.', 'Auction Reminder', 1),
    ('Your payment for item #4567 has been received.', 'Payment Received', 1),
    ('The auction for item #8910 has been canceled.', 'Auction Canceled', 1),
    ('Your feedback on item #2345 is appreciated.', 'Feedback Request', 1);

INSERT INTO tbl_user_has_notification (id_notification, id_user)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (1, 11),
    (2, 12),
    (3, 13),
    (4, 14),
    (5, 15),
    (6, 16),
    (7, 17),
    (8, 18),
    (9, 19),
    (10, 20);

INSERT INTO tbl_information_account (cart_number, expiration, cvv, billing_address, postal_code, state, first_name, last_name, city_account, status, id_user)
VALUES
    ('1234-5678-9012-3456', '2028-12', '123', '123 Main St', '12345', 'NY', 'John', 'Doe', 'New York', 1, 1),
    ('9876-5432-1098-7654', '2029-02', '456', '456 Elm St', '54321', 'CA', 'Jane', 'Smith', 'Los Angeles', 1, 2),
    ('2345-6789-0123-4567', '2028-09', '789', '789 Oak St', '67890', 'TX', 'Michael', 'Johnson', 'Houston', 1, 3),
    ('8765-4321-0987-6543', '2027-11', '234', '234 Pine St', '45678', 'IL', 'Emily', 'Davis', 'Chicago', 1, 4),
    ('3456-7890-1234-5678', '2029-03', '567', '567 Cedar St', '23456', 'AZ', 'David', 'Miller', 'Phoenix', 1, 5),
    ('6543-2109-8765-4321', '2028-07', '890', '890 Walnut St', '78901', 'PA', 'Sophia', 'Wilson', 'Philadelphia', 1, 6),
    ('4567-8901-2345-6789', '2029-05', '321', '321 Birch St', '56789', 'TX', 'James', 'Brown', 'San Antonio', 1, 7),
    ('7654-3210-9876-5432', '2028-04', '654', '654 Maple St', '34567', 'CA', 'Olivia', 'Jones', 'San Diego', 1, 8),
    ('5678-9012-3456-7890', '2027-10', '987', '987 Oak St', '90123', 'TX', 'Benjamin', 'Garcia', 'Dallas', 1, 9),
    ('8765-4321-0987-6543', '2028-12', '210', '210 Pine St', '67890', 'CA', 'Isabella', 'Martinez', 'San Jose', 1, 10),
    ('3210-9876-5432-1098', '2028-08', '543', '543 Elm St', '01234', 'TX', 'Alexander', 'Rodriguez', 'Austin', 1, 11),
    ('8901-2345-6789-0123', '2029-01', '876', '876 Maple St', '89012', 'FL', 'Mia', 'Hernandez', 'Jacksonville', 1, 12),
    ('1098-7654-3210-9876', '2027-03', '109', '109 Cedar St', '56789', 'TX', 'Ethan', 'Martinez', 'Fort Worth', 1, 13),
    ('5678-9012-3456-7890', '2029-09', '432', '432 Birch St', '12345', 'OH', 'Ava', 'Lopez', 'Columbus', 1, 14),
    ('9876-5432-1098-7654', '2028-07', '765', '765 Oak St', '45678', 'NC', 'William', 'Gonzalez', 'Charlotte', 1, 15),
    ('4321-0987-6543-2109', '2028-02', '098', '098 Pine St', '23456', 'IN', 'Harper', 'Perez', 'Indianapolis', 1, 16),
    ('2109-8765-4321-0987', '2027-12', '321', '321 Maple St', '78901', 'CA', 'Daniel', 'Thompson', 'San Francisco', 1, 17),
    ('5432-1098-7654-3210', '2027-05', '654', '654 Cedar St', '34567', 'WA', 'Chloe', 'Anderson', 'Seattle', 1, 18),
    ('8765-4321-0987-6543', '2028-10', '987', '987 Walnut St', '90123', 'CO', 'Matthew', 'Taylor', 'Denver', 1, 19),
    ('1098-7654-3210-9876', '2029-05', '210', '210 Oak St', '01234', 'DC', 'Ella', 'Thomas', 'Washington', 1, 20);

INSERT INTO tbl_transaction_history (transaction_amount, content, sender_account_number, bank, account_owner_name,
                                     receiver_name, recipient_account_number, status_transaction, note, day_trading, status, id_user)
VALUES
    (100.50, 'Payment for services', '1234-5678-9012-3456', 'Bank of America', 'John Doe', 'Jane Smith', '9876-5432-1098-7654', 'SUCCESS', 'Service payment', '2024-07-09', 1, 1),
    (200.75, 'Monthly rent payment', '2345-6789-0123-4567', 'Chase Bank', 'Michael Johnson', 'Emily Davis', '8765-4321-0987-6543', 'SUCCESS', 'Rent for July', '2024-07-08', 1, 2),
    (150.25, 'Grocery shopping', '3456-7890-1234-5678', 'Wells Fargo', 'Jane Smith', 'David Miller', '6543-2109-8765-4321', 'SUCCESS', 'Groceries for the week', '2024-07-07',1, 3),
    (300.00, 'Utility bill payment', '4567-8901-2345-6789', 'US Bank', 'Michael Johnson', 'Sophia Wilson', '3210-9876-5432-1098', 'SUCCESS', 'Electricity bill', '2024-07-06', 1, 4),
    (75.80, 'Internet subscription renewal', '5678-9012-3456-7890', 'Capital One', 'Emily Davis', 'David Miller', '8901-2345-6789-0123', 'SUCCESS', 'Internet service renewal', '2024-07-05', 1, 5),
    (50.00, 'Gift purchase', '6543-2109-8765-4321', 'HSBC Bank', 'David Miller', 'Sophia Wilson', '1098-7654-3210-9876', 'SUCCESS', 'Birthday gift', '2024-07-04', 1, 6),
    (120.45, 'Restaurant dinner', '7654-3210-9876-5432', 'TD Bank', 'Sophia Wilson', 'James Brown', '9876-5432-1098-7654', 'SUCCESS', 'Dinner with friends', '2024-07-03',1, 7),
    (85.60, 'Movie tickets', '8765-4321-0987-6543', 'BB&T Bank', 'James Brown', 'Olivia Jones', '4567-8901-2345-6789', 'SUCCESS', 'Movie night out', '2024-07-02', 1, 8),
    (95.20, 'Shopping spree', '9876-5432-1098-7654', 'PNC Bank', 'Olivia Jones', 'Benjamin Garcia', '7654-3210-9876-5432', 'SUCCESS', 'Shopping for summer', '2024-07-01', 1, 9),
    (200.30, 'Car repair', '1098-7654-3210-9876', 'SunTrust Bank', 'Benjamin Garcia', 'Isabella Martinez', '2345-6789-0123-4567', 'SUCCESS', 'Fixing brakes', '2024-06-30', 1, 10),
    (150.10, 'Flight booking', '2109-8765-4321-0987', 'Regions Bank', 'Isabella Martinez', 'Alexander Rodriguez', '5678-9012-3456-7890', 'SUCCESS', 'Vacation travel', '2024-06-29', 1, 11),
    (80.75, 'Concert tickets', '3210-9876-5432-1098', 'Ally Bank', 'Alexander Rodriguez', 'Mia Hernandez', '8765-4321-0987-6543', 'SUCCESS', 'Live music event', '2024-06-28', 1, 12),
    (175.50, 'Fitness membership', '4321-0987-6543-2109', 'Citizens Bank', 'Mia Hernandez', 'Ethan Martinez', '1098-7654-3210-9876', 'SUCCESS', 'Gym subscription', '2024-06-27', 1, 13),
    (250.20, 'Home appliance purchase', '5432-1098-7654-3210', 'KeyBank', 'Ethan Martinez', 'Ava Lopez', '2109-8765-4321-0987', 'SUCCESS', 'New refrigerator', '2024-06-26', 1, 14),
    (300.90, 'Furniture shopping', '6543-2109-8765-4321', 'M&T Bank', 'Ava Lopez', 'William Gonzalez', '5432-1098-7654-3210', 'SUCCESS', 'Living room set', '2024-06-25', 1, 15),
    (180.60, 'Book purchase', '7654-3210-9876-5432', 'Huntington Bank', 'William Gonzalez', 'Harper Perez', '8765-4321-0987-6543', 'SUCCESS', 'New novels', '2024-06-24', 1, 16),
    (120.30, 'Medical bill payment', '8765-4321-0987-6543', 'First Republic Bank', 'Harper Perez', 'Daniel Thompson', '2109-8765-4321-0987', 'SUCCESS', 'Doctor visit', '2024-06-23',1, 17),
    (90.40, 'Charity donation', '9876-5432-1098-7654', 'BMO Harris Bank', 'Daniel Thompson', 'Chloe Anderson', '6543-2109-8765-4321', 'SUCCESS', 'Local charity', '2024-06-22', 1, 18),
    (110.25, 'Home improvement', '1098-7654-3210-9876', 'Comerica Bank', 'Chloe Anderson', 'Matthew Taylor', '8765-4321-0987-6543', 'SUCCESS', 'New kitchen cabinets', '2024-06-21', 1, 19),
    (95.80, 'Electronics purchase', '2109-8765-4321-0987', 'Union Bank', 'Matthew Taylor', 'Ella Thomas', '1098-7654-3210-9876', 'SUCCESS', 'New laptop', '2024-06-20', 1, 20);

INSERT INTO tbl_auction (auction_name, address, number_of_participants, start_time, end_time, type_auction, auction_status, id_auctioneer, status)
VALUES
    ('Antique Auction', '123 Main St, New York', 50, '2024-08-01 10:00:00', '2024-08-01 14:00:00', 'DIRECT', 'UPCOMING', 9, 1),
    ('Art Auction', '456 Elm St, Los Angeles', 80, '2024-07-15 13:00:00', '2024-07-15 17:00:00', 'ONLINE', 'HAPPENNING', 17, 1),
    ('Car Auction', '789 Oak Ave, Chicago', 30, '2024-07-20 11:00:00', '2024-07-20 15:00:00', 'OFFLINE', 'FINISHED', 9, 1),
    ('Jewelry Auction', '234 Pine St, Houston', 40, '2024-07-25 09:00:00', '2024-07-25 13:00:00', 'DIRECT', 'UPCOMING', 17, 1),
    ('Wine Auction', '567 Maple Dr, Phoenix', 60, '2024-07-10 12:00:00', '2024-07-10 16:00:00', 'ONLINE', 'HAPPENNING', 9, 1),
    ('Real Estate Auction', '890 Cedar Ln, Philadelphia', 25, '2024-07-05 15:00:00', '2024-07-05 19:00:00', 'OFFLINE', 'FINISHED', 17, 1),
    ('Electronics Auction', '321 Birch Rd, San Antonio', 35, '2024-07-30 14:00:00', '2024-07-30 18:00:00', 'DIRECT', 'UPCOMING', 9, 1),
    ('Furniture Auction', '654 Spruce Ave, San Diego', 55, '2024-07-12 16:00:00', '2024-07-12 20:00:00', 'ONLINE', 'HAPPENNING', 17, 1),
    ('Toy Auction', '987 Pineapple Blvd, Dallas', 20, '2024-07-22 10:00:00', '2024-07-22 14:00:00', 'OFFLINE', 'FINISHED', 9, 1),
    ('Sports Memorabilia Auction', '123 Orange Ln, San Jose', 45, '2024-07-18 11:00:00', '2024-07-18 15:00:00', 'DIRECT', 'UPCOMING', 17, 1),
    ('Fashion Auction', '456 Lemon St, Austin', 70, '2024-07-08 13:00:00', '2024-07-08 17:00:00', 'ONLINE', 'HAPPENNING', 9, 1),
    ('Book Auction', '789 Grape Dr, Jacksonville', 28, '2024-07-27 09:00:00', '2024-07-27 13:00:00', 'OFFLINE', 'FINISHED', 17, 1),
    ('Music Instruments Auction', '234 Cherry Ave, Fort Worth', 38, '2024-07-14 12:00:00', '2024-07-14 16:00:00', 'DIRECT', 'UPCOMING', 9, 1),
    ('Antique Furniture Auction', '567 Plum Rd, Columbus', 52, '2024-07-17 14:00:00', '2024-07-17 18:00:00', 'ONLINE', 'HAPPENNING', 17, 1),
    ('Artwork Auction', '890 Mango Blvd, Charlotte', 42, '2024-07-29 11:00:00', '2024-07-29 15:00:00', 'OFFLINE', 'FINISHED', 9, 1),
    ('Photography Auction', '123 Peach Ln, Indianapolis', 32, '2024-07-09 10:00:00', '2024-07-09 14:00:00', 'DIRECT', 'UPCOMING', 17, 1),
    ('Comic Books Auction', '456 Pinecone Dr, San Francisco', 48, '2024-07-26 13:00:00', '2024-07-26 17:00:00', 'ONLINE', 'HAPPENNING', 17, 1),
    ('Coin Auction', '789 Acorn Ave, Seattle', 26, '2024-07-13 15:00:00', '2024-07-13 19:00:00', 'OFFLINE', 'FINISHED', 9, 1),
    ('Automobile Auction', '234 Walnut St, Denver', 36, '2024-07-19 16:00:00', '2024-07-19 20:00:00', 'DIRECT', 'UPCOMING', 9, 1),
    ('Fine Jewelry Auction', '567 Oakwood Rd, Washington', 58, '2024-07-11 09:00:00', '2024-07-11 13:00:00', 'ONLINE', 'HAPPENNING', 17, 1);

INSERT INTO tbl_user_has_auction (deposits, deposits_date, user_has_auction_status, id_auction, id_user, status)
VALUES
    (1200, '2024-07-10', 'JOIN', 1, 3, 1),
    (1500, '2024-07-12', 'CANCEL', 5, 3, 1),
    (800, '2024-07-11', 'JOIN', 3, 5, 1),
    (1000, '2024-07-13', 'CANCEL', 7, 5, 1),
    (1800, '2024-07-14', 'JOIN', 2, 14, 1),
    (2000, '2024-07-15', 'CANCEL', 6, 14, 1),
    (3000, '2024-07-16', 'JOIN', 4, 20, 1),
    (2500, '2024-07-17', 'CANCEL', 8, 20, 1),
    (2200, '2024-07-18', 'JOIN', 10, 3, 1),
    (1800, '2024-07-19', 'CANCEL', 12, 5, 1),
    (2700, '2024-07-20', 'JOIN', 15, 14, 1),
    (1900, '2024-07-21', 'CANCEL', 18, 20, 1);

INSERT INTO tbl_appraisers (experiences, specialized, status_appraisers, id_user, status)
VALUES
    (10, 'Antiques and Fine Art', 'APPRAISED', 6, 1),
    (5, 'Jewelry and Gemstones', 'NOT_YET_EVALUATED', 15, 1);

INSERT INTO tbl_tax (tax_name, tax_type, tax_rate, description, status)
VALUES
    ('Sales Tax', 'Percentage', 8.5, 'Standard sales tax rate for goods in the US', 1),
    ('Property Tax', 'Percentage', 1.2, 'Annual property tax rate based on assessed property value', 1),
    ('Income Tax', 'Progressive', NULL, 'Federal income tax rates vary based on income brackets', 1),
    ('Import Tariff', 'Fixed Amount', 250, 'Tariff on imported electronics goods', 1),
    ('Excise Tax', 'Percentage', 5.0, 'Excise tax on certain goods like alcohol and tobacco', 1);

INSERT INTO tbl_asset (asset_name, description, size, origin, property_status, quantity, status_asset, id_user_winner, id_seller, id_warehouse, id_cate_asset, id_appraisers, status)
VALUES
    ('Antique Vase', 'An ancient Chinese porcelain vase', '19*30*100 cm', 'China', 'Like New', 1, 'SOLD', 3, 2, 1, 4, 1, 1),
    ('Painting', 'A famous oil painting from the 19th century', '120*90 cm', 'France', 'Like New', 1, 'STOCKING', 5, 4, 2, 5, 2, 1),
    ('Vintage Car', 'Classic car from the 1960s in mint condition', '4000*1600*1200 mm', 'USA', 'Like New', 1, 'IN_STOCK', 14, 7, 3, 3, 1, 1),
    ('Diamond Necklace', 'High-quality diamond necklace', '45*20*5 mm', 'India', 'Like New', 1, 'OUT_OF_STOCK', 20, 10, 4, 6, 2, 1),
    ('Rare Coin Collection', 'A collection of ancient coins', '5*5*0.3 cm', 'Italy', 'Like New', 1, 'SOLD', 3, 13, 5, 7, 1, 1),
    ('Vintage Furniture Set', 'Victorian-era furniture set', '200*150*80 cm (table), 100*50*120 cm (chair)', 'UK', 'Like New', 1, 'STOCKING', 5, 18, 6, 8, 2, 1),
    ('Signed Sports Memorabilia', 'Autographed baseball memorabilia', '30*40 cm (poster)', 'USA', 'Like New', 1, 'IN_STOCK', 14, 2, 7, 9, 1, 1),
    ('Rare Book Collection', 'First-edition books from various authors', '25*18*5 cm', 'France', 'Like New', 1, 'OUT_OF_STOCK', 20, 4, 8, 10, 2, 1),
    ('Vintage Wine Collection', 'Rare wine bottles from around the world', '30*10*10 cm (bottle)', 'Italy', 'Like New', 1, 'SOLD', 3, 7, 9, 1, 1, 1),
    ('Antique Clock', 'A beautiful antique grandfather clock', '180*50*30 cm', 'Germany', 'Like New', 1, 'STOCKING', 5, 10, 10, 2, 2, 1),
    ('Art Deco Jewelry', 'Exquisite art deco jewelry pieces', '15*10*5 cm (box)', 'USA', 'Like New', 1, 'IN_STOCK', 14, 13, 1, 3, 1, 1),
    ('Rare Stamp Collection', 'Rare stamps from different countries', '10*8*0.1 cm', 'UK', 'Like New', 1, 'OUT_OF_STOCK', 20, 18, 2, 4, 2, 1),
    ('Vintage Camera Set', 'Collection of vintage cameras', '20*15*10 cm (camera)', 'Japan', 'Like New', 1, 'SOLD', 3, 2, 3, 5, 1, 1),
    ('Fine Art Sculpture', 'Sculpture by a renowned artist', '100*50*50 cm', 'France', 'Like New', 1, 'STOCKING', 5, 4, 4, 6, 2, 1),
    ('Rare Manuscript', 'Ancient manuscript with historical significance', '30*20*5 cm', 'Italy', 'Like New', 1, 'IN_STOCK', 14, 7, 5, 7, 1, 1),
    ('Collectible Toy Set', 'Rare collectible toys from the 1980s', '10*10*5 cm (each toy)', 'USA', 'Like New', 1, 'OUT_OF_STOCK', 20, 10, 6, 8, 2, 1),
    ('Vintage Poster Collection', 'Original vintage posters', '70*50 cm (each poster)', 'France', 'Like New', 1, 'SOLD', 3, 13, 7, 9, 1, 1),
    ('Rare Music Instruments', 'Rare musical instruments from various eras', '120*40*20 cm (violin)', 'UK', 'Like New', 1, 'STOCKING', 5, 8, 8, 10, 2, 1),
    ('Fine Jewelry Collection', 'High-end jewelry collection', '10*10*5 cm (each piece)', 'Italy', 'Like New', 1, 'IN_STOCK', 14, 2, 9, 1, 1, 1),
    ('Antique Furniture', 'Antique furniture pieces', '200*150*80 cm (table), 100*50*120 cm (chair)', 'Germany', 'Like New', 1, 'OUT_OF_STOCK', 20, 4, 10, 2, 2, 1),
    ('Classic Car', 'Classic car restoration project', '4000*1600*1200 mm', 'USA', 'Like New', 1, 'SOLD', 3, 7, 1, 3, 1, 1),
    ('Rare Artwork', 'Rare artworks by famous artists', '120*90 cm', 'France', 'Like New', 1, 'STOCKING', 5, 10, 2, 4, 2, 1),
    ('Vintage Wine', 'Vintage wine bottles', '30*10*10 cm (bottle)', 'Italy', 'Like New', 1, 'IN_STOCK', 14, 13, 3, 5, 1, 1),
    ('Fine Art Painting', 'Fine art painting by a renowned artist', '180*120 cm', 'UK', 'Like New', 1, 'OUT_OF_STOCK', 20, 18, 4, 6, 2, 1),
    ('Rare Coin', 'Rare coins from various historical periods', '5*5*0.3 cm', 'USA', 'Like New', 1, 'SOLD', 3, 2, 5, 7, 1, 1),
    ('Vintage Furniture', 'Vintage furniture set', '200*150*80 cm (table), 100*50*120 cm (chair)', 'France', 'Like New', 1, 'STOCKING', 5, 4, 6, 8, 2, 1),
    ('Signed Sports Memorabilia', 'Autographed sports memorabilia', '30*40 cm (poster)', 'Italy', 'Like New', 1, 'IN_STOCK', 14, 7, 7, 9, 1, 1),
    ('Rare Book', 'Rare first-edition books', '25*18*5 cm', 'Germany', 'Like New', 1, 'OUT_OF_STOCK', 20, 10, 8, 10, 2, 1),
    ('Vintage Wine Collection', 'Collection of vintage wines', '30*10*10 cm (bottle)', 'USA', 'Like New', 1, 'SOLD', 3, 13, 9, 1, 1, 1),
    ('Antique Clock', 'Antique clock from the 19th century', '180*50*30 cm', 'France', 'Like New', 1, 'STOCKING', 5, 18, 10, 2, 2, 1);

INSERT INTO tbl_asset_media (id_asset, link, type, description, status)
VALUES
    (1, 'https://example.com/image1.jpg', 'IMAGES', 'Front view of the asset', 1),
    (2, 'https://example.com/video1.mp4', 'VIDEOS', 'Introduction video of the asset', 1),
    (3, 'https://example.com/image2.jpg', 'IMAGES', 'Side view of the asset', 1),
    (4, 'https://example.com/image3.jpg', 'FILES', 'Top view of the asset', 1);


INSERT INTO tbl_auction_has_asset (id_auction, id_asset, starting_price, present_price, auction_results, status)
VALUES
    (1, 1, 1000, 1200, 'SUCCESSFULLY_SOLD', 1),
    (1, 2, 800, 800, 'SELLING_FAILURE', 1),
    (2, 3, 1500, 1600, 'SUCCESSFULLY_SOLD', 1),
    (2, 4, 1200, 1200, 'SUCCESSFULLY_SOLD', 1),
    (3, 5, 900, 1000, 'SUCCESSFULLY_SOLD', 1),
    (3, 6, 700, 700, 'SELLING_FAILURE', 1),
    (4, 7, 2000, 2100, 'SUCCESSFULLY_SOLD', 1),
    (4, 8, 1800, 1800, 'SUCCESSFULLY_SOLD', 1),
    (5, 9, 3000, 3200, 'SUCCESSFULLY_SOLD', 1),
    (5, 10, 2500, 2500, 'SELLING_FAILURE', 1),
    (6, 11, 1500, 1600, 'SUCCESSFULLY_SOLD', 1),
    (6, 12, 1400, 1400, 'SUCCESSFULLY_SOLD', 1),
    (7, 13, 1800, 1900, 'SUCCESSFULLY_SOLD', 1),
    (7, 14, 1700, 1700, 'SELLING_FAILURE', 1),
    (8, 15, 2500, 2600, 'SUCCESSFULLY_SOLD', 1),
    (8, 16, 2200, 2200, 'SUCCESSFULLY_SOLD', 1),
    (9, 17, 1200, 1300, 'SUCCESSFULLY_SOLD', 1),
    (9, 18, 1100, 1100, 'SELLING_FAILURE', 1),
    (10, 19, 3500, 3700, 'SUCCESSFULLY_SOLD', 1),
    (10, 20, 3200, 3200, 'SUCCESSFULLY_SOLD', 1);

INSERT INTO tbl_fee (fee_name, cost, payment_status, payment_date, id_auction_asset, status)
VALUES
    ('Registration Fee', 50.00, 'PAID', '2024-07-10', 1, 1),
    ('Listing Fee', 100.00, 'PAID', '2024-07-11', 2, 1),
    ('Commission Fee', 250.00, 'PAID', '2024-07-12', 3, 1),
    ('Withdrawal Fee', 20.00, 'PAID', '2024-07-13', 4, 1),
    ('Bidder Fee', 30.00, 'PAID', '2024-07-14', 5, 1),
    ('Seller Fee', 150.00, 'PAID', '2024-07-15', 6, 1),
    ('Transaction Fee', 75.00, 'PAID', '2024-07-16', 7, 1),
    ('Buyers Premium', 200.00, 'PAID', '2024-07-17', 8, 1),
    ('Shipping Fee', 50.00, 'PAID', '2024-07-18', 9, 1),
    ('Handling Fee', 25.00, 'PAID', '2024-07-19', 10, 1),
    ('Insurance Fee', 30.00, 'PAID', '2024-07-20', 11, 1),
    ('Escrow Fee', 100.00, 'PAID', '2024-07-21', 12, 1),
    ('Escrow Release Fee', 50.00, 'PAID', '2024-07-22', 13, 1),
    ('Currency Conversion Fee', 15.00, 'PAID', '2024-07-23', 14, 1),
    ('Late Payment Fee', 40.00, 'PAID', '2024-07-24', 15, 1),
    ('Service Fee', 80.00, 'PAID', '2024-07-25', 16, 1),
    ('Renewal Fee', 120.00, 'PAID', '2024-07-26', 17, 1),
    ('Processing Fee', 35.00, 'PAID', '2024-07-27', 18, 1),
    ('Membership Fee', 300.00, 'PAID', '2024-07-28', 19, 1),
    ('Consultation Fee', 150.00, 'PAID', '2024-07-29', 20, 1);

INSERT INTO tbl_contract (contract_name, contract_date, total_amount, status_contract, id_user, id_auction_asset, status)
VALUES
    ('Contract 1', '2024-07-01', 1500.00, 'COMPLETED', 3, 1, 1),
    ('Contract 2', '2024-07-05', 2000.00, 'COMPLETED', 5, 2, 1),
    ('Contract 3', '2024-07-10', 1800.00, 'CANCELED', 14, 3, 1),
    ('Contract 4', '2024-07-15', 2200.00, 'COMPLETED', 20, 4, 1),
    ('Contract 5', '2024-07-20', 1900.00, 'CANCELED', 3, 5, 1),
    ('Contract 6', '2024-07-25', 2400.00, 'COMPLETED', 5, 6, 1),
    ('Contract 7', '2024-07-30', 2100.00, 'COMPLETED', 14, 7, 1),
    ('Contract 8', '2024-08-03', 2600.00, 'COMPLETED', 20, 8, 1),
    ('Contract 9', '2024-08-08', 2300.00, 'CANCELED', 3, 9, 1),
    ('Contract 10', '2024-08-12', 2800.00, 'COMPLETED', 5, 10, 1),
    ('Contract 11', '2024-08-17', 2500.00, 'COMPLETED', 14, 11, 1),
    ('Contract 12', '2024-08-22', 3000.00, 'COMPLETED', 20, 12, 1),
    ('Contract 13', '2024-08-27', 2700.00, 'CANCELED', 3, 13, 1),
    ('Contract 14', '2024-09-01', 3200.00, 'COMPLETED', 5, 14, 1),
    ('Contract 15', '2024-09-06', 2900.00, 'COMPLETED', 14, 15, 1),
    ('Contract 16', '2024-09-11', 3400.00, 'COMPLETED', 20, 16, 1),
    ('Contract 17', '2024-09-16', 3100.00, 'CANCELED', 3, 17, 1),
    ('Contract 18', '2024-09-21', 3600.00, 'COMPLETED', 5, 18, 1),
    ('Contract 19', '2024-09-26', 3300.00, 'COMPLETED', 14, 19, 1),
    ('Contract 20', '2024-09-30', 3800.00, 'COMPLETED', 20, 20, 1);

INSERT INTO tbl_tax_has_contract (id_tax, id_contract, tax_amount)
VALUES
    (1, 1, 100.00),
    (2, 2, 150.00),
    (3, 3, 120.00),
    (4, 4, 200.00),
    (5, 5, 180.00),
    (1, 6, 110.00),
    (2, 7, 160.00),
    (3, 8, 130.00),
    (4, 9, 220.00),
    (5, 10, 190.00),
    (1, 11, 120.00),
    (2, 12, 170.00),
    (3, 13, 140.00),
    (4, 14, 240.00),
    (5, 15, 210.00),
    (1, 16, 130.00),
    (2, 17, 180.00),
    (3, 18, 150.00),
    (4, 19, 260.00),
    (5, 20, 230.00);

INSERT INTO tbl_bid (id_user, id_auction_asset, bid_amount, bid_time)
VALUES
    (3, 1, 500.00, '2024-07-09 10:00:00'),
    (5, 2, 700.00, '2024-07-09 11:00:00'),
    (14, 3, 600.00, '2024-07-09 12:00:00'),
    (20, 4, 900.00, '2024-07-09 13:00:00'),
    (3, 5, 550.00, '2024-07-09 14:00:00'),
    (5, 6, 750.00, '2024-07-09 15:00:00'),
    (14, 7, 650.00, '2024-07-09 16:00:00'),
    (20, 8, 950.00, '2024-07-09 17:00:00'),
    (3, 9, 600.00, '2024-07-09 18:00:00'),
    (5, 10, 800.00, '2024-07-09 19:00:00'),
    (14, 11, 700.00, '2024-07-09 20:00:00'),
    (20, 12, 1000.00, '2024-07-09 21:00:00'),
    (3, 13, 650.00, '2024-07-09 22:00:00'),
    (5, 14, 850.00, '2024-07-09 23:00:00'),
    (14, 15, 750.00, '2024-07-10 10:00:00'),
    (20, 16, 1100.00, '2024-07-10 11:00:00'),
    (3, 17, 700.00, '2024-07-10 12:00:00'),
    (5, 18, 900.00, '2024-07-10 13:00:00'),
    (14, 19, 800.00, '2024-07-10 14:00:00'),
    (20, 20, 1200.00, '2024-07-10 15:00:00');

INSERT INTO tbl_holidays (holiday_date, holiday_name, holiday_type, status)
VALUES
    ('2025-01-01', 'New Year''s Day', 'Federal', 1),
    ('2025-01-20', 'Martin Luther King, Jr. Day', 'Federal', 1),
    ('2025-02-17', 'Presidents'' Day', 'Federal', 1),
    ('2025-05-26', 'Memorial Day', 'Federal', 1),
    ('2025-07-04', 'Independence Day', 'Federal', 1),
    ('2025-09-01', 'Labor Day', 'Federal', 1),
    ('2025-10-13', 'Columbus Day', 'Federal', 1),
    ('2025-11-11', 'Veterans Day', 'Federal', 1),
    ('2025-11-27', 'Thanksgiving Day', 'Federal', 1),
    ('2025-12-25', 'Christmas Day', 'Federal', 1),
    ('2025-03-31', 'Cesar Chavez Day', 'State', 1), -- California
    ('2025-06-25', 'Kamehameha Day', 'State', 1); -- Hawaii
