package br.edu.unicesumar.folia.controller;

public class Conversor {

    public static String obterNomeBanco(int codigo) {
        switch (codigo) {
            case 0: return "Nenhum";
            case 1: return "Banco do Brasil";
            case 2: return "Santander";
            case 3: return "Caixa Econômica Federal (Convênio SIGCB)";
            case 4: return "Caixa Econômica Federal (Convênio SICOB)";
            case 5: return "Bradesco";
            case 6: return "Itaú";
            case 7: return "Banco Mercantil";
            case 8: return "Sicred";
            case 9: return "Bancoob";
            case 10: return "Banrisul";
            case 11: return "Banestes";
            case 12: return "HSBC";
            case 13: return "Banco do Nordeste";
            case 14: return "Banco BRB";
            case 15: return "BicBanco";
            case 16: return "BradescoSICOOB";
            case 17: return "BancoSafra";
            case 18: return "SafraBradesco";
            case 19: return "BancoCECRED";
            case 20: return "BancoDaAmazonia";
            case 21: return "BancoDoBrasilSICOOB";
            case 22: return "Uniprime";
            case 23: return "UnicredRS";
            case 24: return "Banese";
            case 25: return "CrediSIS";
            case 26: return "UnicredES";
            case 27: return "BancoCresolSCRS";
            case 28: return "CitiBank";
            case 29: return "BancoABCBrasil";
            case 30: return "Daycoval";
            case 31: return "UniprimeNortePR";
            case 32: return "BancoPine";
            case 33: return "BancoPineBradesco";
            case 34: return "UnicredSC";
            case 35: return "BancoAlfa";
            case 36: return "BancoDoBrasilAPI";
            case 37: return "BancoDoBrasilWS";
            case 38: return "BancoCresol";
            case 39: return "MoneyPlus";
            case 40: return "BancoC6";
            case 41: return "BancoRendimento";
            case 42: return "BancoInter";
            case 43: return "BancoSofisaSantander";
            case 44: return "BS2";
            case 45: return "PenseBankAPI";
            case 46: return "BTGPactual";
            case 47: return "BancoOriginal";
            case 48: return "BancoVotorantin";
            case 49: return "BancoPefisa";
            case 50: return "BancoFibra";
            case 51: return "BancoSofisaItau";
            case 52: return "BancoIndustrialBrasil";
            case 53: return "BancoAthenaBradesco";
            case 54: return "BancoQITechSCD";
            case 55: return "BancoUY3";
            case 56: return "BancoBocomBBM";
            case 57: return "BancoSicoob";
            case 58: return "BancoSisprime";
            case 59: return "BancoAilos";
            default: return "Código inválido";
        }
    }


}
