-- =========================================
-- TIPOS DE CUENTA (idempotente sin tocar id existente)
-- =========================================
INSERT INTO account_type (code, name, estado, id)
SELECT 'SAVINGS','Cuenta de ahorros','ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM account_type WHERE code='SAVINGS');

INSERT INTO account_type (code, name, estado, id)
SELECT 'CHECKING','Cuenta corriente','ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM account_type WHERE code='CHECKING');

INSERT INTO account_type (code, name, estado, id)
SELECT 'EDE','Depósito electrónico','ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM account_type WHERE code='EDE');

-- =========================================
-- BANCOS (idempotente sin tocar id existente)
-- =========================================
INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1303','860006797-9','Banco Union', TRUE, FALSE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1303');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1001','860002964-4','Banco de Bogotá', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1001');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1002','860007738-9','Banco Popular', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1002');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1006','890903937-0','Itaú antes Corpbanca', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1006');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1007','890903938-8','Bancolombia', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1007');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1009','860051135-4','Banco Citibank', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1009');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1013','860003020-1','BBVA Colombia', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1013');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1014','860007660-3','Itaú', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1014');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1019','860034594-1','Scotiabank Colpatria', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1019');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1023','890300279-4','Banco de Occidente', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1023');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1051','860034313-7','Banco Davivienda', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1051');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1052','860035827-5','Banco AV Villas', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1052');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1053','900378212-2','Banco W S.A.', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1053');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1059','900215071-1','Bancamia S.A.', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1059');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1060','890200756-7','Banco Pichincha S.A.', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1060');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1061','900406150-5','Bancoomeva', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1061');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1062','900047981-8','Banco Falabella', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1062');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1066','890203088-9','Banco Coopcentral', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1066');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1069','860043186-6','Banco Serfinanza', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1069');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1070','901383474-9','Lulo Bank S. A.', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1070');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1292','890981395-1','Confiar Cooperativa Financiera', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1292');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1370','890927034-9','Coltefinanciera', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1370');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1507','890903938-8','NEQUI', TRUE, FALSE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1507');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1551','860034313-7','Daviplata', FALSE, FALSE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1551');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1558','900200960-9','BAN100 (Credifinanciera)', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1558');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1560','890200756-7','PIBANK', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1560');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1637','811007729-4','FINANCIERA DANN REGIONAL - IRIS', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1637');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1801','901077952-6','Movii', FALSE, FALSE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1801');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1805','901491551-0','Banco BTG Pactual', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1805');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1802','901119606-4','DING TECNIPAGOS SA', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1802');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1803','901077411-3','PAGOS GDE S.A. POWWI', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1803');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1804','901502301-5','UALÁ', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1804');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1811','901526741-6','RAPPIPAY', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1811');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1812','901198878-8','COINK S.A.', FALSE, FALSE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1812');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1814','901429272-8','GLOBAL66', TRUE, TRUE, TRUE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1814');

INSERT INTO bank (code, nit, name, allows_savings, allows_checking, allows_electronic, estado, id)
SELECT '1809','901284129-8','NU BANK', TRUE, TRUE, FALSE, 'ACTIVO', RANDOM_UUID()
WHERE NOT EXISTS (SELECT 1 FROM bank WHERE code='1809');

-- =========================================
-- REGLAS DE LONGITUD (insert solo si no existe)
-- =========================================
INSERT INTO bank_account_rule (id, bank_id, account_type_id, min_length, max_length)
SELECT RANDOM_UUID(), b.id, t.id, v.min_len, v.max_len
FROM (
  VALUES
    ('1303','SAVINGS',17,17),
    ('1001','SAVINGS',9,11),('1001','CHECKING',9,11),
    ('1002','SAVINGS',9,12),('1002','CHECKING',9,12),
    ('1006','SAVINGS',9,9), ('1006','CHECKING',9,9),
    ('1007','SAVINGS',11,11),('1007','CHECKING',11,11),
    ('1009','SAVINGS',10,10),('1009','CHECKING',10,10),
    ('1013','SAVINGS',9,16),('1013','CHECKING',9,16),('1013','EDE',9,16),
    ('1014','SAVINGS',9,9), ('1014','CHECKING',9,9),
    ('1019','SAVINGS',10,12),('1019','CHECKING',10,12),
    ('1023','SAVINGS',9,9), ('1023','CHECKING',9,9),
    ('1051','SAVINGS',12,16),('1051','CHECKING',12,16),
    ('1052','SAVINGS',9,13),('1052','CHECKING',9,13),
    ('1053','SAVINGS',12,17),('1053','CHECKING',12,17),
    ('1059','SAVINGS',17,17),('1059','CHECKING',17,17),
    ('1060','SAVINGS',9,9), ('1060','CHECKING',9,9),
    ('1066','SAVINGS',9,13),('1066','CHECKING',9,13),
    ('1069','SAVINGS',12,12),('1069','CHECKING',12,12),
    ('1070','SAVINGS',12,12),('1070','CHECKING',12,12),
    ('1292','SAVINGS',9,9), ('1292','CHECKING',9,9),
    ('1370','SAVINGS',6,6), ('1370','CHECKING',6,6),
    ('1507','SAVINGS',10,11),('1507','EDE',10,11),
    ('1551','EDE',10,10),
    ('1558','SAVINGS',13,13),('1558','CHECKING',13,13),
    ('1560','SAVINGS',12,12),('1560','CHECKING',12,12),
    ('1637','SAVINGS',12,12),('1637','CHECKING',12,12),
    ('1801','EDE',10,10),
    ('1805','SAVINGS',11,11),('1805','CHECKING',11,11),
    ('1802','SAVINGS',6,12),('1802','CHECKING',6,12),('1802','EDE',6,12),
    ('1803','SAVINGS',10,10),('1803','CHECKING',10,10),('1803','EDE',10,10),
    ('1804','SAVINGS',10,15),('1804','CHECKING',10,15),('1804','EDE',10,15),
    ('1811','SAVINGS',9,10),('1811','CHECKING',9,10),('1811','EDE',9,10),
    ('1812','EDE',10,11),
    ('1814','SAVINGS',10,10),('1814','CHECKING',10,10),('1814','EDE',10,10),
    ('1809','SAVINGS',8,8), ('1809','CHECKING',8,8)
) AS v(code, type_code, min_len, max_len)
JOIN bank b ON b.code = v.code
JOIN account_type t ON t.code = v.type_code
WHERE NOT EXISTS (
  SELECT 1 FROM bank_account_rule r
  WHERE r.bank_id = b.id AND r.account_type_id = t.id
);

-- =========================================
-- CUENTAS DEMO (insert si no existe)
-- =========================================
INSERT INTO account (id, bank_id, account_type_id, alias, account_number, phone_number,
                     holder_doc_type, holder_doc_number, holder_full_name, estado, created_at, updated_at)
SELECT RANDOM_UUID(), b.id, t.id, 'Ahorros Dani', '12345678901', NULL,
       'CC', '11111111', 'Daniel Zárate', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM bank b, account_type t
WHERE b.code='1007' AND t.code='SAVINGS'
  AND NOT EXISTS (
    SELECT 1 FROM account a
    WHERE a.bank_id=b.id AND a.account_type_id=t.id AND a.account_number='12345678901'
  );

INSERT INTO account (id, bank_id, account_type_id, alias, account_number, phone_number,
                     holder_doc_type, holder_doc_number, holder_full_name, estado, created_at, updated_at)
SELECT RANDOM_UUID(), b.id, t.id, NULL, '98765432109', NULL,
       'CC', '22222222', 'María López', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM bank b, account_type t
WHERE b.code='1001' AND t.code='CHECKING'
  AND NOT EXISTS (
    SELECT 1 FROM account a
    WHERE a.bank_id=b.id AND a.account_type_id=t.id AND a.account_number='98765432109'
  );

INSERT INTO account (id, bank_id, account_type_id, alias, account_number, phone_number,
                     holder_doc_type, holder_doc_number, holder_full_name, estado, created_at, updated_at)
SELECT RANDOM_UUID(), b.id, t.id, 'Nequi Dani', NULL, '3001234567',
       'CC', '11111111', 'Daniel Zárate', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM bank b, account_type t
WHERE b.code='1507' AND t.code='EDE'
  AND NOT EXISTS (
    SELECT 1 FROM account a
    WHERE a.bank_id=b.id AND a.account_type_id=t.id AND a.phone_number='3001234567'
  );

INSERT INTO account (id, bank_id, account_type_id, alias, account_number, phone_number,
                     holder_doc_type, holder_doc_number, holder_full_name, estado, created_at, updated_at)
SELECT RANDOM_UUID(), b.id, t.id, 'BBVA Ahorros', '12345000001', NULL,
       'CC', '33333333', 'Juan Pérez', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM bank b, account_type t
WHERE b.code='1013' AND t.code='SAVINGS'
  AND NOT EXISTS (
    SELECT 1 FROM account a
    WHERE a.bank_id=b.id AND a.account_type_id=t.id AND a.account_number='12345000001'
  );
