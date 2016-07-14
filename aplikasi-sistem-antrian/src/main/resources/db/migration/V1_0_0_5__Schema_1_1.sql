CREATE TABLE IF NOT EXISTS `t_pasien` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `blood_type` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `height` int(11) NOT NULL,
  `identity_number` varchar(255) NOT NULL,
  `identity_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `no_pasien` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `updated_date` date NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k4dckuj5tynq9x1bq70kwpjtd` (`identity_number`),
  UNIQUE KEY `UK_3jcb93l2vg225lcqxty99kpv4` (`no_pasien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_pasien_history` (
  `id` varchar(255) NOT NULL,
  `complaints` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `transaction_date` timestamp NOT NULL,
  `id_pasien` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h9m33ln7k1sbjtjfs9r5muf16` (`id_pasien`),
  CONSTRAINT `FK_h9m33ln7k1sbjtjfs9r5muf16` FOREIGN KEY (`id_pasien`) REFERENCES `t_pasien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_obat_history` (
  `id` varchar(255) NOT NULL,
  `buyer_name` varchar(255) NOT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `total_pembelian` decimal(19,2) NOT NULL,
  `transaction_date` timestamp NOT NULL,
  `id_pasien` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_16tj37ouxswf4y8cm4mdulw7e` (`id_pasien`),
  CONSTRAINT `FK_16tj37ouxswf4y8cm4mdulw7e` FOREIGN KEY (`id_pasien`) REFERENCES `t_pasien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_obat_history_detail` (
  `id` varchar(255) NOT NULL,
  `harga` decimal(19,2) NOT NULL,
  `qty` int(11) NOT NULL,
  `total` decimal(19,2) NOT NULL,
  `unit_type` varchar(255) NOT NULL,
  `id_history_obat` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gtb6dva87v08ykvvpuvrxa85w` (`id_history_obat`),
  CONSTRAINT `FK_gtb6dva87v08ykvvpuvrxa85w` FOREIGN KEY (`id_history_obat`) REFERENCES `t_obat_history` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;