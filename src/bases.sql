CREATE TABLE `sklad`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date_start` DATE NOT NULL,
  `date_end` DATE NOT NULL,
  `project_price` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO sklad.orders values (null, 'ДС 268', '2016-03-01', '2016-03-16', 2790);
INSERT INTO sklad.orders values (null, 'Снайперов, 8-46', '2016-03-04', '2016-03-12', 2000);

ALTER TABLE `sklad`.`orders`
ADD COLUMN `comment` BLOB NULL AFTER `project_price`;
ALTER TABLE `sklad`.`orders`
CHANGE COLUMN `comment` `comment` VARCHAR(255) NULL DEFAULT NULL ;

INSERT INTO sklad.orders values (null, 'Кавалерийская, 3-6', '2016-03-09', '2016-03-19', 2000, 'Кухня. ДСП: Белая, Фасады: Белый гладкий, Сигнал Оранжевый');
INSERT INTO sklad.orders values (null, 'RedCup', '2016-03-09', '2016-03-17', 350, 'база прям.  800х860х550 со столешкой');
INSERT INTO sklad.orders values (null, 'Бармалей', '2016-03-14', '2016-03-17', 1000, 'Две кривые столешки, соединенные мет.планкой, надстройка к столу. Столешки Венге 26. Надстройка: любой ДСП16');
update sklad.orders SET comment='Кровати Волна, OF-044 (1шт), OF-008 (1шт), OF-003 (1шт). Кровати Фанера Белая, Синяя. Шкафы: Клен 16' WHERE id=1;

INSERT INTO sklad.orders values (null, 'Бармалей', '2016-03-14', '2016-03-17', 1000, 'Две кривые столешки, соединенные мет.планкой, надстройка к столу. Столешки Венге 26. Надстройка: любой ДСП16');
INSERT INTO sklad.orders values (null, 'Рабоче-Кр. 24-48', '2016-03-14', '2016-03-24', 1500, 'Кровать 2яр с ящ и выдвижной столешкой. Дуб Молочный, Фисташка, Лимон');
INSERT INTO sklad.orders values (null, 'Лобаново, Зеленая, 3/1Б-37', '2016-03-16', '2016-03-25', 1000, 'Гардеробная. Клен16');
INSERT INTO sklad.orders values (null, 'Носков Павел', '2016-03-16', '2016-03-16', 80, 'Распил');
INSERT INTO sklad.orders values (null, 'Аптека Виталенд', '2016-03-16', '2016-04-01', 2280, 'Шкафы. Желтый, Ирис');

#2016-03-29:
CREATE TABLE `sellers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sellers_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `seller_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seller_id` (`seller_id`),
  CONSTRAINT `fk_seller_id` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sklad.sellers VALUES (null, 'Фиерра'), (null, 'Рондо'), (null, 'Партнер'), (null, 'Нэко'), (null, 'ПермЭкспоСервис');
SELEct * FROM sklad.sellers ORDER BY name;

# Новая таблица "Списания":
CREATE TABLE `sklad`.`minus` (
  `order_id` INT NOT NULL,
  `furniture_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `date` DATE NOT NULL,
  INDEX `fk_order_id_idx` (`order_id` ASC),
  INDEX `fk_furniture_id_idx` (`furniture_id` ASC),
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `sklad`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_furniture_id`
    FOREIGN KEY (`furniture_id`)
    REFERENCES `sklad`.`furniture` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SELECT furniture.name as 'Фурнитура', minus.amount as 'Количество', orders.name as 'Заказ', minus.date as 'Дата'
FROM orders, furniture, minus
WHERE minus.order_id = orders.id AND minus.furniture_id = furniture.id;
