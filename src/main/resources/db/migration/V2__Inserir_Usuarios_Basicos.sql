-- Inserir Endereços
INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO_RESIDENCIAL, COMPLEMENTO, BAIRRO, CIDADE, CEP, UF)
VALUES ('a1b2c3d4-e5f6-7a8b-9c0d-e1f2a3b4c5d6', 'Rua das Flores', '123', 'Apto 101', 'Jardim das Acácias', 'São Paulo', '01000000', 'SP'),
       ('b2c3d4e5-f6a7-8b9c-0d1e-f2a3b4c5d6e7', 'Avenida Central', '456', '', 'Centro', 'Rio de Janeiro', '20000000', 'RJ'),
       ('c3d4e5f6-a7b8-9c0d-1e2f-a3b4c5d6e7f8', 'Praça da Sé', '789', 'Sala 202', 'Sé', 'São Paulo', '01001000', 'SP'),
       ('d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90', 'Rua dos Coqueiros', '321', 'Casa', 'Vila Nova', 'Campinas', '13000000', 'SP'),
       ('e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f90a1', 'Rua das Palmeiras', '654', '', 'Bela Vista', 'São Paulo', '01002000', 'SP');

-- Inserir Empresas
INSERT INTO EMPRESA (ID, NOME_FANTASIA, RAZAO_SOCIAL, CNPJ, EMAIL, TELEFONE, ENDERECO_FK)
VALUES
    ('d6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', 'Empresa A', 'Razao Social A', '12345678000101', 'contato@empresaa.com', '5544999999999',
        'a1b2c3d4-e5f6-7a8b-9c0d-e1f2a3b4c5d6'),
    ('e7f8a9b0-c1d2-3d4e-5f6a-8b9c0d1e2f3a', 'Empresa B', 'Razao Social B', '98765432000102', 'contato@empresab.com', '5544888888888',
        'b2c3d4e5-f6a7-8b9c-0d1e-f2a3b4c5d6e7'),
    ('f8a9b0c1-d2e3-4e5f-6a7b-9c0d1e2f3a4b', 'Empresa C', 'Razao Social C', '19283746000103', 'contato@empresac.com', '5544777777777',
        'c3d4e5f6-a7b8-9c0d-1e2f-a3b4c5d6e7f8'),
    ('a9b0c1d2-e3f4-5f6a-7b8c-0d1e2f3a4b5c', 'Empresa D', 'Razao Social D', '56473829000104', 'contato@empresad.com', '5544666666666',
        'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90'),
    ('b0c1d2e3-f4a5-6a7b-8c9d-1e2f3a4b5c6d', 'Empresa E', 'Razao Social E', '10293847560105', 'contato@empresae.com', '5544555555555',
        'e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f90a1');

-- Inserir Usuários com referência aos endereços e empresas
INSERT INTO USUARIO (ID, IDENTIFICACAO, NOME, EMAIL, USERNAME, SENHA, TIPO_USUARIO, ENDERECO_FK, EMPRESA_FK)
VALUES
    ('e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', 'ID001', 'Vinicius A', 'vinicius.a@email.com', 'vinicius', 'senha123', 'ADMIN',
        'a1b2c3d4-e5f6-7a8b-9c0d-e1f2a3b4c5d6', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f'),
    ('f2a3b4c5-d6e7-8f90-a1b2-c3d4e5f6a7b8', 'ID002', 'Gabriel B', 'gabriel.b@email.com', 'gabriel', 'senha456', 'USER',
        'b2c3d4e5-f6a7-8b9c-0d1e-f2a3b4c5d6e7', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f'),
    ('a3b4c5d6-e7f8-90a1-b2c3-d4e5f6a7b8c9', 'ID003', 'Fernando C', 'fernando.c@email.com', 'fernando', 'senha789', 'USER',
        'c3d4e5f6-a7b8-9c0d-1e2f-a3b4c5d6e7f8', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f'),
    ('b4c5d6e7-f8a9-0a1b-2c3d-e5f6a7b8c9d0', 'ID004', 'Yasmin D', 'yasmin.d@email.com', 'yasmin', 'senha012', 'ADMIN',
        'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f'),
    ('c5d6e7f8-a9b0-1b2c-3d4e-f6a7b8c9d0e1', 'ID005', 'Luana E', 'luana.v@email.com', 'luana', 'senha345', 'USER',
        'e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f90a1', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f');

-- Inserir Banco
INSERT INTO BANCO (ID, NOME, AGENCIA, AGENCIA_DIGITO, CONTA, CONTA_DIGITO)
VALUES ('f6a7b8c9-d0e1-2f3a-4b5c-6d7e8f90a1b2', 'ITAU TESTE', '1234', '5', '987654', '3'),
       ('a7b8c9d0-e1f2-3a4b-5c6d-7e8f90a1b2c3', 'SANTANDER TESTE', '5678', '9', '654321', '7'),
       ('b8c9d0e1-f2a3-4b5c-6d7e-8f90a1b2c3d4', 'BRADESCO TESTE', '9101', '2', '123456', '1'),
       ('c9d0e1f2-a3b4-5c6d-7e8f-90a1b2c3d4e5', 'SICOOB TESTE', '1122', '3', '543210', '8'),
       ('d0e1f2a3-b4c5-6d7e-8f90-a1b2c3d4e5f6', 'DAYCOVAL TESTE', '3344', '6', '789012', '4');

-- Inserir Boleto
INSERT INTO BOLETO (ID, BANCO_FK, VALOR, DATA_EMISSAO, DATA_VENCIMENTO, TOTAL_PARCELAS, CONVENIO, CODIGO_CEDENTE, CODIGO_TRANSMISSAO,
                    MODALIDADE,
                    RESPONSAVEL_EMISSAO, TIPO_CARTEIRA, TIPO_DOCUMENTO, USUARIO_FK, CARACTERISTICA_TITULO,
                    CODIGO_NEGATIVACAO, EMPRESA_FK,
                    IDENTIFICACAO_DISTRIBUICAO, OPERACAO, CHAVE_PIX, TIPO_CHAVE_PIX, STATUS, URL)
VALUES ('c1d2e3f4-a5b6-7b8c-9d0e-2f3a4b5c6d7e', 'f6a7b8c9-d0e1-2f3a-4b5c-6d7e8f90a1b2', '1500.00',
        CURRENT_DATE - INTERVAL '15' DAY, CURRENT_DATE + INTERVAL '10' DAY, '12', '10', '05', '08', '01', '02',
        '03', '04', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '01', '02', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '01',
        'Operacao1', 'chavePix1', '01',
        'ABERTO', 'https://boleto1.com.br'),
       ('d2e3f4a5-b6c7-8c9d-0e1f-3a4b5c6d7e8f', 'a7b8c9d0-e1f2-3a4b-5c6d-7e8f90a1b2c3', '2500.00',
        CURRENT_DATE - INTERVAL '10' DAY, CURRENT_DATE + INTERVAL '5' DAY, '06', '12', '06', '09', '02', '03',
        '04', '05', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '02', '03', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '02',
        'Operacao2', 'chavePix2', '02',
        'PAGO', 'https://boleto2.com.br'),
       ('e3f4a5b6-c7d8-9d0e-1f2a-4b5c6d7e8f90', 'b8c9d0e1-f2a3-4b5c-6d7e-8f90a1b2c3d4', '3500.00',
        CURRENT_DATE - INTERVAL '20' DAY, CURRENT_DATE - INTERVAL '3' DAY, '08', '14', '07', '10', '03', '04',
        '05', '06', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '03', '04', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '03',
        'Operacao3', 'chavePix3', '03',
        'VENCIDO', 'https://boleto3.com.br'),
       ('f4a5b6c7-d8e9-0e1f-2a3b-5c6d7e8f90a1', 'c9d0e1f2-a3b4-5c6d-7e8f-90a1b2c3d4e5', '4500.00',
        CURRENT_DATE - INTERVAL '5' DAY, CURRENT_DATE + INTERVAL '5' DAY, '10', '16', '08', '11', '04', '05',
        '06', '07', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '04', '05', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '04',
        'Operacao4', 'chavePix4', '04',
        'ABERTO', 'https://boleto4.com.br'),
       ('a5b6c7d8-e9f0-1f2a-3b4c-6d7e8f90a1b2', 'd0e1f2a3-b4c5-6d7e-8f90-a1b2c3d4e5f6', '5500.00',
        CURRENT_DATE - INTERVAL '10' DAY, CURRENT_DATE + INTERVAL '3' DAY, '04', '18', '09', '12', '05', '06',
        '07', '08', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '05', '06', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '05',
        'Operacao5', 'chavePix5', '05',
        'PAGO', 'https://boleto5.com.br'),
       ('c953834d-1da7-4db3-bafc-51d849df6004', 'f6a7b8c9-d0e1-2f3a-4b5c-6d7e8f90a1b2', '3000.00',
        CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '15' DAY, '15', '11', '04', '07', '01', '02',
        '03', '06', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '01', '02', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '01',
        'Operacao6', 'chavePix6', '01',
        'ABERTO', 'https://boleto6.com.br'),
       ('34c6091c-9a8c-4537-82cf-d1a61210a8dd', 'a7b8c9d0-e1f2-3a4b-5c6d-7e8f90a1b2c3', '4000.00',
        CURRENT_DATE - INTERVAL '8' DAY, CURRENT_DATE + INTERVAL '8' DAY, '03', '12', '07', '10', '02', '03',
        '04', '07', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '02', '03', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '02',
        'Operacao7', 'chavePix7', '02',
        'PAGO', 'https://boleto7.com.br'),
       ('8bb04988-d014-4e80-a44e-19a1edaaebe9', 'a7b8c9d0-e1f2-3a4b-5c6d-7e8f90a1b2c3', '5000.00',
        CURRENT_DATE - INTERVAL '2' DAY, CURRENT_DATE + INTERVAL '2' DAY, '10', '14', '08', '11', '03', '04',
        '05', '08', 'e1f2a3b4-c5d6-7e8f-90a1-b2c3d4e5f6a7', '03', '04', 'd6e7f8a9-b0c1-2c3d-4e5f-7a8b9c0d1e2f', '03',
        'Operacao8', 'chavePix8', '03',
        'ABERTO', 'https://boleto8.com.br');

-- Inserir Conta Bancaria
INSERT INTO CONTA_BANCARIA (ID, BANCO_FK, NUMERO_CONTA, AGENCIA, TITULAR, CPF_CNPJ, TIPO_CONTA, CODIGO_IBAN, DATA_ABERTURA, TELEFONE_CONTATO,
                            EMAIL_TITULAR)
VALUES ('b6c7d8e9-f0a1-2a3b-4c5d-7e8f90a1b2c3', 'f6a7b8c9-d0e1-2f3a-4b5c-6d7e8f90a1b2', '987654', '1234', 'João Silva', '12345678909', 'CORRENTE',
        'BR1234567890123456789001', '2024-01-01', '11987654321', 'joao.silva@example.com'),
       ('c7d8e9f0-a1b2-3b4c-5d6e-8f90a1b2c3d4', 'a7b8c9d0-e1f2-3a4b-5c6d-7e8f90a1b2c3', '654321', '5678', 'Maria Oliveira', '98765432100', 'POUPANCA',
        'BR1234567890123456789002', '2024-02-01', '11987654322', 'maria.oliveira@example.com'),
       ('d8e9f0a1-b2c3-4c5d-6e7f-9a0b1c2d3e4f', 'b8c9d0e1-f2a3-4b5c-6d7e-8f90a1b2c3d4', '123456', '9101', 'Carlos Souza', '12312312312', 'CORRENTE',
        'BR1234567890123456789003', '2024-03-01', '11987654323', 'carlos.souza@example.com'),
       ('e9f0a1b2-c3d4-5d6e-7f8a-0b1c2d3e4f5a', 'c9d0e1f2-a3b4-5c6d-7e8f-90a1b2c3d4e5', '543210', '1122', 'Ana Costa', '32132132198', 'POUPANCA',
        'BR1234567890123456789004', '2024-04-01', '11987654324', 'ana.costa@example.com'),
       ('f0a1b2c3-d4e5-6e7f-8a9b-1c2d3e4f5a6b', 'd0e1f2a3-b4c5-6d7e-8f90-a1b2c3d4e5f6', '789012', '3344', 'Pedro Almeida', '45645645645', 'CORRENTE',
        'BR1234567890123456789005', '2024-05-01', '11987654325', 'pedro.almeida@example.com');
