-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 28-Maio-2019 às 14:30
-- Versão do servidor: 10.1.39-MariaDB
-- versão do PHP: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `caixaeletronico`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `id_conta` int(11) NOT NULL,
  `Num_conta` int(12) NOT NULL,
  `Nome` varchar(150) NOT NULL,
  `CPF` varchar(12) NOT NULL,
  `Senha` int(5) NOT NULL,
  `Senha2` int(5) DEFAULT NULL,
  `Senha3` int(5) DEFAULT NULL,
  `Tipo_Conta` int(2) NOT NULL,
  `Saldo` int(50) DEFAULT NULL,
  `Valor_maximo` int(5) NOT NULL,
  `Valor_minimo` int(5) NOT NULL,
  `status` varchar(3) DEFAULT NULL,
  `Bloc` int(3) NOT NULL,
  `Token` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`id_conta`, `Num_conta`, `Nome`, `CPF`, `Senha`, `Senha2`, `Senha3`, `Tipo_Conta`, `Saldo`, `Valor_maximo`, `Valor_minimo`, `status`, `Bloc`, `Token`) VALUES
(1, 12345, 'Gustavo', '08990305993', 55806, 54321, 12345, 1, 4449, 1000, 1, '1', 0, 966),
(2, 12346, 'Gustavo', '08990305993', 55806, 54321, 12345, 0, 1016, 1000, 1, '1', 0, 789),
(3, 12344, 'Gustavo', '08990305993', 55806, 54321, 12345, 2, 1945, 1000, 1, '1', 0, 852),
(4, 63154, 'Teste', '42342834012', 12345, NULL, NULL, 0, 0, 1000, 1, '1', 0, 746),
(5, 32198, 'Teste', '42342834012', 12345, NULL, NULL, 1, 0, 1000, 1, '1', 0, 327),
(6, 96345, 'teste', '42342834012', 12345, NULL, NULL, 2, 0, 1000, 1, '1', 0, 610),
(7, 16897, 'Teste2', '82038598010', 12345, NULL, NULL, 2, 0, 1000, 1, '1', 0, 945);

-- --------------------------------------------------------

--
-- Estrutura da tabela `log`
--

CREATE TABLE `log` (
  `log_id` int(11) NOT NULL,
  `log_NumConta` int(11) NOT NULL,
  `log_valor` int(20) NOT NULL,
  `log_transacao` varchar(50) NOT NULL,
  `log_data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `log`
--

INSERT INTO `log` (`log_id`, `log_NumConta`, `log_valor`, `log_transacao`, `log_data`) VALUES
(35, 12346, 100, 'Saque', '2019-05-22'),
(36, 12346, 666, 'Depósito', '2019-05-22'),
(37, 12346, 333, 'Saque', '2019-05-22'),
(38, 12345, 100, 'Transaferencia Saida', '2019-05-22'),
(39, 12345, 100, 'Transaferencia Entrada', '2019-05-22'),
(40, 12346, 303, 'Transaferencia Saida', '2019-05-22'),
(41, 12345, 303, 'Transaferencia Entrada', '2019-05-22'),
(42, 12345, 122, 'Depósito', '2019-05-22'),
(43, 12345, 321, 'Saque', '2019-05-22'),
(44, 12345, 367, 'Transaferencia Saida', '2019-05-22'),
(45, 12346, 367, 'Transaferencia Entrada', '2019-05-22'),
(46, 16897, 0, 'Criação da Conta', '2019-05-24'),
(47, 12346, 100, 'Depósito', '2019-05-24'),
(48, 12346, 100, 'Saque', '2019-05-24'),
(49, 12346, 130, 'Transaferencia Saida', '2019-05-24'),
(50, 12345, 130, 'Transaferencia Entrada', '2019-05-24'),
(51, 12346, 350, 'Saque', '2019-05-24'),
(52, 12346, 350, 'Depósito', '2019-05-24'),
(53, 12346, 350, 'Depósito', '2019-05-24'),
(54, 12346, 100, 'Transaferencia Saida', '2019-05-24'),
(55, 12345, 100, 'Transaferencia Entrada', '2019-05-24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`id_conta`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`log_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `id_conta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
