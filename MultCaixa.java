import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MultCaixa{
    public static void main(String[] args){
        //iniciando as variaveis globais
        int operacoes=0, password=0, saldo=10000, tentativas=0;
        int[] banco={5000, 2000, 1000, 500};
        //iniciando o looping do caixa
        do{
            //se a tentativa exceder mais de 2 vezes entao saia de imediato
            if(tentativas>2){
                JOptionPane.showMessageDialog(null, "Erraste mais de tres vezes\n tua conta foi bloqueada");
                break;
            }
            //pegando a palavra passe digitada pelo usuario
         password = Integer.parseInt(JOptionPane.showInputDialog("Tentativas: "+tentativas+"\n Digite a tua senha ou 0 para sair", ""));
            
            //verificando se a palavra passe é válida
            if(password==1234){
                //se a passe for válida reinicie a tentativa apartir do zero
                tentativas=0;

                //iniciando um novo looping de operacoes
                do{
                    //pedindo para usuario que escolha uma das operacoes
                operacoes = Integer.parseInt(JOptionPane.showInputDialog("1-Ver saldo da conta\n2-Depositar\n3-Levantar\n4-Sair"));
                
                //validando a escolha
                switch(operacoes){
                    //caso for 1 mostrar o saldo da conta
                    case 1:
                        JOptionPane.showMessageDialog(null, "Teu saldo: "+saldo);
                        break;
                    case 2:
                    //caso for 2 pedir que deposite algo na conta
                        saldo+=Integer.parseInt(JOptionPane.showInputDialog("Digite o valor", ""));
                        break;
                    case 3:
                        int sacar = Integer.parseInt(JOptionPane.showInputDialog("Quanto queres sacar?", ""));
                        if(sacar>saldo){
                            JOptionPane.showMessageDialog(null, "O teu saldo nao e sufucinte para sacar esse valor");
                        }else{
                            String dinheiroMostrar="";
                            int dinheiroText=0, limite= 10;
                            do{
                                System.out.println("estou no primeiro do while: "+sacar);
                                for(int i=0; i<3; i++){
                                System.out.println("estou no primeiro for: "+i);

                                    if(banco[i]<=sacar){
                                System.out.println("estou no if banco[i]<=sacar: "+banco[i]);

                                        dinheiroText+=banco[i];
                                System.out.println("quanto saquei: "+dinheiroText);

                                        if(dinheiroText>sacar){
                                System.out.println("estou no dinheiroText>sacar --: "+dinheiroText);

                                            dinheiroText-=banco[i];
                                        }else if(dinheiroText==sacar){
                                System.out.println("---------\nterminado pela operacao concluida: "+dinheiroText);
                                                dinheiroMostrar+=" "+banco[i]+",";
                                            break;
                                        }else{
                                            boolean mais=true;
                                            while(mais){
                                                dinheiroMostrar+=" "+banco[i]+",";
                                                dinheiroText+=banco[i];
                                                if(dinheiroText>sacar){
                                                    System.out.println("estou no while dinheiroText>sacar --: "+dinheiroText);

                                                    dinheiroText-=banco[i];
                                                    mais=false;
                                        }
                                            }
                                        }
                                        
                                    }
                                }
                                System.out.println("---------\nsai do for: "+dinheiroText);
                                if(dinheiroText==sacar){
                                System.out.println("---------\nACABOU O SAQUE: "+dinheiroText);
                                saldo-=dinheiroText;
                                JOptionPane.showMessageDialog(null,"Requisitaste: "+sacar+"\nNotas sacadas: "+dinheiroMostrar+"\nTotal sacado: "+dinheiroText+"\nSaldo actual: "+saldo);
                                if(Integer.parseInt(JOptionPane.showInputDialog("Desejas Imprimir a factura?\n[1]-> Sim\n[0]-> Nao",""))==1){
                                    File factura = new File("Factura.txt");
                                    try{
                                        FileWriter escrevendo = new FileWriter(factura);
                                        escrevendo.write("Requisitaste: "+sacar+"\nNotas sacadas: "+dinheiroMostrar+"\nTotal sacado: "+dinheiroText+"\nSaldo actual: "+saldo+"\n------------------------\nPrograma criado por Jose Artur Kassala\nTel: 995377451/932693623");
                                        escrevendo.flush();
                                        escrevendo.close();
                                    }catch(IOException e){
                                        JOptionPane.showMessageDialog(null,"Já existe uma factura nesse directorio\nPorfavor, Apague a factura existente e volte a fazer a operacao");
                                    }
                                }
                                    break;
                                }
                                limite--;
                                if(limite<1){
                                System.out.println("---------\nterminado pelo excesso de requisicoes: "+limite);

                                    break;

                                }

                            }while(dinheiroText==sacar);
                        }
                        break;
                    case 4:
                        operacoes=0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Valor nao existente");
                        break;
                }
                //o looping so para quando na escolha o usuario escolher 0 para sair e ir a tela de senha
                }while(operacoes!=0);
                
                //se o usuario errar mostrar a tela de palavra passe erradada e acrescentar no numero de tentativas
            }else if(password!=0){
                JOptionPane.showMessageDialog(null,"Palavra passe errada");
                tentativas+=1;
            }

//o caixa so para quando o usua digitar 0 em vez de palavra passe
        }while(password!=0);
        
    }
}