create database if not exists stockapp;
use stockapp;
CREATE TABLE IF NOT EXISTS `produse` (
    `id` int primary key not null auto_increment,
    `nume_produs` varchar(20),
    `stoc` int
);
CREATE TABLE IF NOT EXISTS `comenzi` (
    `id` int primary key not null auto_increment,
    `nume_client` varchar(20),
    `produse_id` int,
    `status_comanda` ENUM('ACCEPTAT', 'STOC_INSUFICIENT')
);
alter table comenzi add constraint fk_produse foreign key (produse_id) references produse(id);

INSERT INTO `produse` (`id`, `nume_produs`, `stoc`) VALUES
    (1, 'Faina', '20'),
    (2, 'Zahar', '15'),
    (3, 'Ulei', '17'),
    (4, 'Paste', '23'),
    (5, 'Orez', '12');

INSERT INTO `comenzi` (`id`, `nume_client`,`produse_id` ,`status_comanda`) VALUES
    (1, 'Ionescu Georgiana', 1,'ACCEPTAT'),
    (2, 'Georgescu Iuliana', 3,'ACCEPTAT'),
    (3, 'Marinescu Adriana',5 ,'ACCEPTAT'),
    (4, 'Popescu Mirela', 3,'STOC_INSUFICIENT'),
    (5, 'Vasilescu Bianca',4 ,'STOC_INSUFICIENT');



