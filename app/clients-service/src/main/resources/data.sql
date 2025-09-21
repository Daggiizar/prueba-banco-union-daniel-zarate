-- =========================
-- TIPOS DE CUENTA
-- =========================
INSERT INTO account_type (id, code, name, estado) VALUES
 (RANDOM_UUID(), 'SAVINGS',  'Cuenta de ahorros', 'ACTIVO'),
 (RANDOM_UUID(), 'CHECKING', 'Cuenta corriente',  'ACTIVO'),
 (RANDOM_UUID(), 'EDE',      'Depósito electrónico', 'ACTIVO');

-- =========================
-- BANCOS (subset inicial)
-- code | nit | name | allows_savings | allows_checking | allows_electronic
-- =========================
INSERT INTO bank (id, code, nit, name, allows_savings, allows_checking, allows_electronic, estado) VALUES
 (RANDOM_UUID(), '1303', '860006797-9', 'Banco Union', TRUE,  FALSE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1001', '860002964-4', 'Banco de Bogotá', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1002', '860007738-9', 'Banco Popular', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1006', '890903937-0', 'Itaú antes Corpbanca', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1007', '890903938-8', 'Bancolombia', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1009', '860051135-4', 'Banco Citibank', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1013', '860003020-1', 'BBVA Colombia', TRUE, TRUE, TRUE,  'ACTIVO'),
 (RANDOM_UUID(), '1014', '860007660-3', 'Itaú', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1019', '860034594-1', 'Scotiabank Colpatria', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1023', '890300279-4', 'Banco de Occidente', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1051', '860034313-7', 'Banco Davivienda', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1052', '860035827-5', 'Banco AV Villas', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1053', '900378212-2', 'Banco W S.A.', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1059', '900215071-1', 'Bancamia S.A.', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1060', '890200756-7', 'Banco Pichincha S.A.', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1061', '900406150-5', 'Bancoomeva', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1062', '900047981-8', 'Banco Falabella', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1066', '890203088-9', 'Banco Coopcentral', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1069', '860043186-6', 'Banco Serfinanza', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1070', '901383474-9', 'Lulo Bank S. A.', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1292', '890981395-1', 'Confiar Cooperativa Financiera', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1370', '890927034-9', 'Coltefinanciera', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1507', '890903938-8', 'NEQUI', TRUE, FALSE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1551', '860034313-7', 'Daviplata', FALSE, FALSE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1558', '900200960-9', 'BAN100 (Credifinanciera)', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1560', '890200756-7', 'PIBANK', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1637', '811007729-4', 'FINANCIERA DANN REGIONAL - IRIS', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1801', '901077952-6', 'Movii', FALSE, FALSE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1805', '901491551-0', 'Banco BTG Pactual', TRUE, TRUE, FALSE, 'ACTIVO'),
 (RANDOM_UUID(), '1802', '901119606-4', 'DING TECNIPAGOS SA', TRUE, TRUE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1803', '901077411-3', 'PAGOS GDE S.A. POWWI', TRUE, TRUE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1804', '901502301-5', 'UALÁ', TRUE, TRUE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1811', '901526741-6', 'RAPPIPAY', TRUE, TRUE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1812', '901198878-8', 'COINK S.A.', FALSE, FALSE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1814', '901429272-8', 'GLOBAL66', TRUE, TRUE, TRUE, 'ACTIVO'),
 (RANDOM_UUID(), '1809', '901284129-8', 'NU BANK', TRUE, TRUE, FALSE, 'ACTIVO');

-- =========================
-- REGLAS DE LONGITUD (subset)
-- Usa WHERE b.code = 'xxxx' y t.code IN (...)
-- =========================

-- Banco Union (1303): sólo ahorros 17
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 17, 17 FROM bank b, account_type t
WHERE b.code='1303' AND t.code='SAVINGS';

-- Banco de Bogotá (1001): A/C 9-11
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 11 FROM bank b, account_type t
WHERE b.code='1001' AND t.code IN ('SAVINGS','CHECKING');

-- Banco Popular (1002): A/C 9-12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 12 FROM bank b, account_type t
WHERE b.code='1002' AND t.code IN ('SAVINGS','CHECKING');

-- Itaú antes Corpbanca (1006): A/C 9
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 9 FROM bank b, account_type t
WHERE b.code='1006' AND t.code IN ('SAVINGS','CHECKING');

-- Bancolombia (1007): A/C 11
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 11, 11 FROM bank b, account_type t
WHERE b.code='1007' AND t.code IN ('SAVINGS','CHECKING');

-- Citibank (1009): A/C 10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 10 FROM bank b, account_type t
WHERE b.code='1009' AND t.code IN ('SAVINGS','CHECKING');

-- BBVA (1013): A/C/EDE 9-16
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 16 FROM bank b, account_type t
WHERE b.code='1013' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- Itaú (1014): A/C 9
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 9 FROM bank b, account_type t
WHERE b.code='1014' AND t.code IN ('SAVINGS','CHECKING');

-- Colpatria (1019): A/C 10-12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 12 FROM bank b, account_type t
WHERE b.code='1019' AND t.code IN ('SAVINGS','CHECKING');

-- Occidente (1023): A/C 9
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 9 FROM bank b, account_type t
WHERE b.code='1023' AND t.code IN ('SAVINGS','CHECKING');

-- Davivienda (1051): A/C 12-16
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 16 FROM bank b, account_type t
WHERE b.code='1051' AND t.code IN ('SAVINGS','CHECKING');

-- AV Villas (1052): A/C 9-13
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 13 FROM bank b, account_type t
WHERE b.code='1052' AND t.code IN ('SAVINGS','CHECKING');

-- Banco W (1053): A/C 12-17
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 17 FROM bank b, account_type t
WHERE b.code='1053' AND t.code IN ('SAVINGS','CHECKING');

-- Bancamía (1059): A/C 17
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 17, 17 FROM bank b, account_type t
WHERE b.code='1059' AND t.code IN ('SAVINGS','CHECKING');

-- Pichincha (1060): A/C 9
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 9 FROM bank b, account_type t
WHERE b.code='1060' AND t.code IN ('SAVINGS','CHECKING');

-- Coopcentral (1066): A/C 9-13
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 13 FROM bank b, account_type t
WHERE b.code='1066' AND t.code IN ('SAVINGS','CHECKING');

-- Serfinanza (1069): A/C 12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 12 FROM bank b, account_type t
WHERE b.code='1069' AND t.code IN ('SAVINGS','CHECKING');

-- Lulo (1070): A/C 12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 12 FROM bank b, account_type t
WHERE b.code='1070' AND t.code IN ('SAVINGS','CHECKING');

-- Confiar (1292): A/C 9
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 9 FROM bank b, account_type t
WHERE b.code='1292' AND t.code IN ('SAVINGS','CHECKING');

-- Coltefinanciera (1370): A/C 6
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 6, 6 FROM bank b, account_type t
WHERE b.code='1370' AND t.code IN ('SAVINGS','CHECKING');

-- NEQUI (1507): SAVINGS/EDE 10-11
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 11 FROM bank b, account_type t
WHERE b.code='1507' AND t.code IN ('SAVINGS','EDE');

-- Daviplata (1551): EDE 10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 10 FROM bank b, account_type t
WHERE b.code='1551' AND t.code='EDE';

-- BAN100 (1558): A/C 13
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 13, 13 FROM bank b, account_type t
WHERE b.code='1558' AND t.code IN ('SAVINGS','CHECKING');

-- PIBANK (1560): A/C 12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 12 FROM bank b, account_type t
WHERE b.code='1560' AND t.code IN ('SAVINGS','CHECKING');

-- IRIS (1637): A/C 12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 12, 12 FROM bank b, account_type t
WHERE b.code='1637' AND t.code IN ('SAVINGS','CHECKING');

-- Movii (1801): EDE 10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 10 FROM bank b, account_type t
WHERE b.code='1801' AND t.code='EDE';

-- BTG (1805): A/C 11
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 11, 11 FROM bank b, account_type t
WHERE b.code='1805' AND t.code IN ('SAVINGS','CHECKING');

-- DING TECNIPAGOS (1802): A/C/EDE 6-12
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 6, 12 FROM bank b, account_type t
WHERE b.code='1802' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- POWWI (1803): A/C/EDE 10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 10 FROM bank b, account_type t
WHERE b.code='1803' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- UALÁ (1804): A/C/EDE 10-15
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 15 FROM bank b, account_type t
WHERE b.code='1804' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- RAPPIPAY (1811): A/C/EDE 9-10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 9, 10 FROM bank b, account_type t
WHERE b.code='1811' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- COINK (1812): EDE 10-11
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 11 FROM bank b, account_type t
WHERE b.code='1812' AND t.code='EDE';

-- GLOBAL66 (1814): A/C/EDE 10
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 10, 10 FROM bank b, account_type t
WHERE b.code='1814' AND t.code IN ('SAVINGS','CHECKING','EDE');

-- NU BANK (1809): A/C 8
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, 8, 8 FROM bank b, account_type t
WHERE b.code='1809' AND t.code IN ('SAVINGS','CHECKING');
