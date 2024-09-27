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









