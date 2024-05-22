/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author dito
 */
public class modeltabeldatabuku extends AbstractTableModel{

    List<databuku> dabuk;
    public modeltabeldatabuku(List<databuku>dabuk){
        this.dabuk = dabuk;
    }
    
    @Override
    public int getRowCount() {
        return dabuk.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "Judul";
            case 2:
                return "Penulis";
            case 3:
                return "Rating";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dabuk.get(row).getId();
            case 1:
                return dabuk.get(row).getJudul();
            case 2:
                return dabuk.get(row).getPenulis();
            case 3:
                return dabuk.get(row).getRating();
            case 4:
                return dabuk.get(row).getHarga();
            default:
                return null;
        }
    }
    
}
