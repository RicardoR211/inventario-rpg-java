package com.meuprojeto.inventario.model.enums;

//Definindo quais os efeitos ele poderá ter
//E cada um dos efeitos implementa o metodo abstrato
public enum TipoEfeito {
    CURA {
        @Override
        public void aplicar(int intensidade) {
            System.out.println(">> Você se curou em " + intensidade + " pontos de vida!");
        }
    },
    AUMENTAR_FORCA{
        @Override
        public void aplicar(int intensidade) {
            System.out.println(">> Sua força aumentou " + intensidade + " por 30 segundos!");
        }
    },
    RESTAURAR_MANA{
        @Override
        public void aplicar(int intensidade) {
            System.out.println(">> Você restaurou " + intensidade + " pontos de mana!");
        }
    },
    GANHAR_DINHEIRO{
        @Override
        public void aplicar(int intensidade) {
            System.out.println(">> Você ganhou " + intensidade + " de grana!");
        }
    };

    //Criando a função que cada constante irá sobrescrever
    public abstract void aplicar(int intensidade);
}
