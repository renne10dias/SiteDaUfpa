CREATE TABLE IF NOT EXISTS `tb_noticia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` TEXT NOT NULL,
  `descricao` TEXT NOT NULL,
  `texto` TEXT NOT NULL,
  `imagem` TEXT NOT NULL,
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `tb_categoria` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `tb_noticia_has_tb_categoria` (
  `tb_noticia_id` BIGINT NOT NULL,
  `tb_categoria_id` BIGINT NOT NULL,
  PRIMARY KEY (`tb_noticia_id`, `tb_categoria_id`),
  INDEX `fk_tb_noticia_has_tb_categoria_tb_categoria1_idx` (`tb_categoria_id` ASC) VISIBLE,
  INDEX `fk_tb_noticia_has_tb_categoria_tb_noticia_idx` (`tb_noticia_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_noticia_has_tb_categoria_tb_noticia`
    FOREIGN KEY (`tb_noticia_id`)
    REFERENCES `tb_noticia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_noticia_has_tb_categoria_tb_categoria1`
    FOREIGN KEY (`tb_categoria_id`)
    REFERENCES `tb_categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `tb_categoria_has_tb_noticia` (
  `tb_categoria_id` BIGINT NOT NULL,
  `tb_noticia_id` BIGINT NOT NULL,
  PRIMARY KEY (`tb_categoria_id`, `tb_noticia_id`),
  INDEX `fk_tb_categoria_has_tb_noticia_tb_noticia1_idx` (`tb_noticia_id` ASC) VISIBLE,
  INDEX `fk_tb_categoria_has_tb_noticia_tb_categoria1_idx` (`tb_categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_categoria_has_tb_noticia_tb_categoria1`
    FOREIGN KEY (`tb_categoria_id`)
    REFERENCES `tb_categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_categoria_has_tb_noticia_tb_noticia1`
    FOREIGN KEY (`tb_noticia_id`)
    REFERENCES `tb_noticia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
