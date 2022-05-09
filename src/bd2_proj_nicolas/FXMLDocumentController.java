package bd2_proj_nicolas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    static Connect conexao = new Connect();

    // Primeira aba
    @FXML private Label labelTab1; 
    @FXML private TextField textField1Tab1; @FXML private TextField textField2Tab1; @FXML private TextField textField3Tab1; @FXML private TextField textField4Tab1; 
    @FXML private TextField textField5Tab1; @FXML private TextField textField6Tab1; @FXML private TextField textField7Tab1; @FXML private TextField textField8Tab1;
    @FXML private TextField textField9Tab1; @FXML private TextField textField10Tab1; @FXML private TextField textField11Tab1; @FXML private TextField textField12Tab1;
    @FXML private TextField textField13Tab1; @FXML private TextField textField14Tab1; @FXML private TextField textField15Tab1; @FXML private TextField textField16Tab1;
    @FXML private TextField textField17Tab1;
    @FXML private ChoiceBox<String> choiceBoxTab1;
    
    // Segunda aba
    @FXML private Label scrollLabel1Tab3; @FXML private Label scrollLabel2Tab3; @FXML private Label scrollLabel3Tab3; @FXML private Label scrollLabel4Tab3; 
    @FXML private Label scrollLabel5Tab3; @FXML private Label scrollLabel6Tab3; @FXML private Label scrollLabel7Tab3; @FXML private Label scrollLabel8Tab3;
    @FXML private Label scrollLabel9Tab3; @FXML private Label scrollLabel10Tab3; @FXML private Label scrollLabel11Tab3; @FXML private Label scrollLabel12Tab3;
    @FXML private Label scrollLabel13Tab3; @FXML private Label scrollLabel14Tab3; @FXML private Label scrollLabel15Tab3; @FXML private Label scrollLabel16Tab3;
    @FXML private Label scrollLabel17Tab3; @FXML private Label scrollLabel18Tab3;
    @FXML private Label label1Tab3; @FXML private Label label2Tab3; @FXML private Label label3Tab3; @FXML private Label label4Tab3;
    @FXML private Label label5Tab3; @FXML private Label label6Tab3; @FXML private Label label7Tab3; @FXML private Label label8Tab3; @FXML private Label label9Tab3; 
    @FXML private Label label10Tab3; @FXML private Label label11Tab3; @FXML private Label label12Tab3; @FXML private Label label13Tab3; @FXML private Label label14Tab3;
    @FXML private Label label15Tab3; @FXML private Label label16Tab3; @FXML private Label label17Tab3; @FXML private Label label18Tab3;
    
    @FXML
    private void connectAction(){
        conexao.conectar();
    }
    
    @FXML
    public void insert(){
        Tabelas tab = Tabelas.valueOfName(choiceBoxTab1.getValue());
        
        TextField texts[] = {textField1Tab1, textField2Tab1, textField3Tab1, textField4Tab1, textField5Tab1, textField6Tab1,  textField7Tab1,  textField8Tab1,
                             textField9Tab1,  textField10Tab1,  textField11Tab1,  textField12Tab1, textField13Tab1,  textField14Tab1,  textField15Tab1,  
                             textField16Tab1,textField17Tab1};
        String values[] = new String[tab.getNumCol() - 1];
        
        for(int i = 0; i < values.length; i++){
            values[i] = texts[i].getText();
        }
        
        int i = conexao.geralInsert(tab.VALUE_ID, values);
        if(i == 0){
            labelTab1.setText("Falha no INSERT. A chave estrangeira não existe ou, atributos fornecidos inválidos.");
        }else{
            labelTab1.setText("INSERT realizado. Os resultados (incluindo possíveis modificações de triggers) estão na aba ''Resultados'' ");
        }
        
        System.out.println(i);       
        selectAll();
    }
    
    @FXML
    public void delete(){
        Tabelas tab = Tabelas.valueOfName(choiceBoxTab1.getValue());
        int i = conexao.delete(tab.VALUE_ID, textField1Tab1.getText());
    }
    
    @FXML
    public void update(){
        Tabelas tab = Tabelas.valueOfName(choiceBoxTab1.getValue());
        
        TextField texts[] = {textField1Tab1, textField2Tab1, textField3Tab1, textField4Tab1, textField5Tab1, textField6Tab1,  textField7Tab1,  textField8Tab1,
                             textField9Tab1,  textField10Tab1,  textField11Tab1,  textField12Tab1, textField13Tab1,  textField14Tab1,  textField15Tab1,  
                             textField16Tab1,textField17Tab1};
        String values[] = new String[tab.getNumCol()];
        
        for(int i = 0; i < values.length; i++){
            values[i] = texts[i].getText();
        }
        
        int i = conexao.geralUpdate(tab.VALUE_ID, values);
        if(i == 0){
            labelTab1.setText("Falha no UPDATE. A chave estrangeira não existe ou, atributos fornecidos inválidos.");
        }else{
            labelTab1.setText("UPDATE realizado. Os resultados (incluindo possíveis modificações de triggers) estão na aba ''Resultados'' ");
        }
        
        System.out.println(i);       
        selectAll();
    }          
    
    @FXML
    public void selectAll(){
        Label scrollLabels[] = {scrollLabel1Tab3, scrollLabel2Tab3, scrollLabel3Tab3, scrollLabel4Tab3, scrollLabel5Tab3, scrollLabel6Tab3, scrollLabel7Tab3, scrollLabel8Tab3,
                                scrollLabel9Tab3, scrollLabel10Tab3, scrollLabel11Tab3, scrollLabel12Tab3, scrollLabel13Tab3, scrollLabel14Tab3, scrollLabel15Tab3, scrollLabel16Tab3,
                                scrollLabel17Tab3, scrollLabel18Tab3};
        Label labels[] = {label1Tab3, label2Tab3, label3Tab3, label4Tab3, label5Tab3,  label6Tab3,  label7Tab3,  label8Tab3,  label9Tab3, 
                          label10Tab3,  label11Tab3,  label12Tab3,  label13Tab3,  label14Tab3, label15Tab3,  label16Tab3,  label17Tab3,  label18Tab3};
        
        for(int i = 0; i < scrollLabels.length; i++){
            scrollLabels[i].setText("");
            labels[i].setText("");
        }
        
        Tabelas tab = Tabelas.valueOfName(choiceBoxTab1.getValue());
        String[] attributes = tab.getAttributes();
        
        for(int i = 0; i < attributes.length; i++){
            labels[i].setText(attributes[i]);
        }
        
        String s = conexao.geralSelect(tab.VALUE_ID);
        String aux = "";
        int col = 0;
        int maxCol = tab.getNumCol();
        int count = 0;
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '|'){
                aux = aux + s.charAt(i);
            }else{
                count ++;
                if(count == 1){
                    continue;
                }else{
                    count = 0;
                }
                if(col >= maxCol){
                    col = 1;
                }else{
                    col ++;
                }
                
                scrollLabels[col-1].setText(scrollLabels[col-1].getText() + aux + "\n");
                System.out.println(aux);
                aux = "";
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList list = FXCollections.observableArrayList();
        Tabelas[] tabs = Tabelas.values();
        
        for(int i = 0; i < tabs.length; i++){
            list.add(tabs[i].getNome());
        }
        choiceBoxTab1.getItems().addAll(list);
        
    }    
    
}
