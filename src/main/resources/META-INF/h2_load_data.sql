INSERT INTO `AMDI_24`.`person` (`nif`, `name`, `place`) VALUES ('11223344A', 'Manuel', 'Madrid');
INSERT INTO `AMDI_24`.`person` (`nif`, `name`, `place`) VALUES ('11223344B', 'Paula', 'Pontevedra');
INSERT INTO `AMDI_24`.`person` (`nif`, `name`, `place`) VALUES ('11223344C', 'Alex', 'Madrid');
INSERT INTO `AMDI_24`.`person` (`nif`, `name`, `place`) VALUES ('11223344D', 'Elena', 'A Coruña');

INSERT INTO `AMDI_24`.`mail` (`address`, `id_person`) VALUES ('manu@gmail.com', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344A'));
INSERT INTO `AMDI_24`.`mail` (`address`, `id_person`) VALUES ('manuel@gmail.com', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344A'));
INSERT INTO `AMDI_24`.`mail` (`address`, `id_person`) VALUES ('pau.lita@gmail.com', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344B'));
INSERT INTO `AMDI_24`.`mail` (`address`, `id_person`) VALUES ('job.paula@gmail.com', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344B'));
INSERT INTO `AMDI_24`.`mail` (`address`, `id_person`) VALUES ('alex@gmail.com', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344C'));

INSERT INTO `AMDI_24`.`phone` (`phone`, `id_person`) VALUES ('981981981', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344A'));
INSERT INTO `AMDI_24`.`phone` (`phone`, `id_person`) VALUES ('666555444', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344A'));
INSERT INTO `AMDI_24`.`phone` (`phone`, `id_person`) VALUES ('916680749', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344C'));
INSERT INTO `AMDI_24`.`phone` (`phone`, `id_person`) VALUES ('677677677', (SELECT `id` FROM `AMDI_24`.`person` WHERE nif = '11223344D'));