-- Inserir Endereços
INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO_RESIDENCIAL, COMPLEMENTO, BAIRRO, CIDADE, CEP, UF) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'Rua das Flores', '123', 'Apto 101', 'Jardim das Acácias', 'São Paulo', '01000000', 'SP'),
('550e8400-e29b-41d4-a716-446655440001', 'Avenida Central', '456', '', 'Centro', 'Rio de Janeiro', '20000000', 'RJ'),
('550e8400-e29b-41d4-a716-446655440002', 'Praça da Sé', '789', 'Sala 202', 'Sé', 'São Paulo', '01001000', 'SP'),
('550e8400-e29b-41d4-a716-446655440003', 'Rua dos Coqueiros', '321', 'Casa', 'Vila Nova', 'Campinas', '13000000', 'SP'),
('550e8400-e29b-41d4-a716-446655440004', 'Rua das Palmeiras', '654', '', 'Bela Vista', 'São Paulo', '01002000', 'SP');

-- Inserir Usuários com referência aos endereços
INSERT INTO USUARIO (ID, IDENTIFICACAO, NOME, EMAIL, USUARIO, SENHA, TIPO_USUARIO, ENDERECO_FK) VALUES
('550e8400-e29b-41d4-a716-446655440100', 'ID001', 'Vinicius A', 'vinicius.a@email.com', 'vinicius', 'senha123', 'ADMIN', '550e8400-e29b-41d4-a716-446655440000'),
('550e8400-e29b-41d4-a716-446655440101', 'ID002', 'Gabriel B', 'gabriel.b@email.com', 'gabriel', 'senha456', 'USER', '550e8400-e29b-41d4-a716-446655440001'),
('550e8400-e29b-41d4-a716-446655440102', 'ID003', 'Fernando C', 'fernando.c@email.com', 'fernando', 'senha789', 'USER', '550e8400-e29b-41d4-a716-446655440002'),
('550e8400-e29b-41d4-a716-446655440103', 'ID004', 'Yasmin D', 'yasmin.d@email.com', 'yasmin', 'senha012', 'ADMIN', '550e8400-e29b-41d4-a716-446655440003'),
('550e8400-e29b-41d4-a716-446655440104', 'ID005', 'Luana E', 'luana.v@email.com', 'luana', 'senha345', 'USER', '550e8400-e29b-41d4-a716-446655440004');

--Inserir Empresa
INSERT INTO EMPRESA (ID, NOME_FANTASIA, RAZAO_SOCIAL, CNPJ, EMAIL, SENHA, ENDERECO_FK) VALUES
('550e8400-e29b-41d4-a716-446655440100', 'Empresa A', 'Razao Social A', '12345678000101', 'contato@empresaa.com', 'senhaSegura1', '550e8400-e29b-41d4-a716-446655440000'),
('550e8400-e29b-41d4-a716-446655440102', 'Empresa B', 'Razao Social B', '98765432000102', 'contato@empresab.com', 'senhaSegura2', '550e8400-e29b-41d4-a716-446655440001'),
('550e8400-e29b-41d4-a716-446655440103', 'Empresa C', 'Razao Social C', '19283746000103', 'contato@empresac.com', 'senhaSegura3', '550e8400-e29b-41d4-a716-446655440002'),
('550e8400-e29b-41d4-a716-446655440104', 'Empresa D', 'Razao Social D', '56473829000104', 'contato@empresad.com', 'senhaSegura4', '550e8400-e29b-41d4-a716-446655440003'),
('550e8400-e29b-41d4-a716-446655440105', 'Empresa E', 'Razao Social E', '10293847560105', 'contato@empresae.com', 'senhaSegura5', '550e8400-e29b-41d4-a716-446655440004');


-- Inserir Banco
INSERT INTO BANCO (ID, AGENCIA, AGENCIA_DIGITO, CONTA, CONTA_DIGITO, DIGITO_VERIFICADOR) VALUES
('550e8400-e29b-41d4-a716-446655440200', '1234', '5', '987654', '3', '1234'),
('550e8400-e29b-41d4-a716-446655440201', '5678', '9', '654321', '7', '5678'),
('550e8400-e29b-41d4-a716-446655440202', '9101', '2', '123456', '1', '9101'),
('550e8400-e29b-41d4-a716-446655440203', '1122', '3', '543210', '8', '1122'),
('550e8400-e29b-41d4-a716-446655440204', '3344', '6', '789012', '4', '3344');

--Inserir Boleto
INSERT INTO BOLETO (ID, BANCO_FK, VALOR, DATA_VENCIMENTO, TOTAL_PARCELAS, CONVENIO, CODIGO_CEDENTE, CODIGO_TRANSMISSAO, MODALIDADE, RESPONSAVEL_EMISSAO, TIPO_CARTEIRA, TIPO_DOCUMENTO, USUARIO_FK, CARAC_TITULO, CODIGO_NEGATIVACAO, EMPRESA_FK, IDENT_DISTRIBUICAO, OPERACAO, CHAVE_PIX, TIPO_CHAVE_PIX, STATUS, URL) VALUES
('550e8400-e29b-41d4-a716-446655440100', '550e8400-e29b-41d4-a716-446655440200', '1500.00', '20241230', '12', '10', '05', '08', '01', '02', '03', '04', '550e8400-e29b-41d4-a716-446655440100', '01', '02', '550e8400-e29b-41d4-a716-446655440100', '01', 'Operacao1', 'chavePix1', '01', 'ABERTO', 'https://boleto1.com.br'),
('550e8400-e29b-41d4-a716-446655440101', '550e8400-e29b-41d4-a716-446655440201', '2500.00', '20241130', '06', '12', '06', '09', '02', '03', '04', '05', '550e8400-e29b-41d4-a716-446655440101', '02', '03', '550e8400-e29b-41d4-a716-446655440102', '02', 'Operacao2', 'chavePix2', '02', 'PAGO', 'https://boleto2.com.br'),
('550e8400-e29b-41d4-a716-446655440102', '550e8400-e29b-41d4-a716-446655440202', '3500.00', '20241030', '08', '14', '07', '10', '03', '04', '05', '06', '550e8400-e29b-41d4-a716-446655440102', '03', '04', '550e8400-e29b-41d4-a716-446655440103', '03', 'Operacao3', 'chavePix3', '03', 'VENCIDO', 'https://boleto3.com.br'),
('550e8400-e29b-41d4-a716-446655440103', '550e8400-e29b-41d4-a716-446655440203', '4500.00', '20240930', '10', '16', '08', '11', '04', '05', '06', '07', '550e8400-e29b-41d4-a716-446655440103', '04', '05', '550e8400-e29b-41d4-a716-446655440104', '04', 'Operacao4', 'chavePix4', '04', 'ABERTO', 'https://boleto4.com.br'),
('550e8400-e29b-41d4-a716-446655440104', '550e8400-e29b-41d4-a716-446655440204', '5500.00', '20240830', '04', '18', '09', '12', '05', '06', '07', '08', '550e8400-e29b-41d4-a716-446655440104', '05', '06', '550e8400-e29b-41d4-a716-446655440105', '05', 'Operacao5', 'chavePix5', '05', 'PAGO', 'https://boleto5.com.br');













