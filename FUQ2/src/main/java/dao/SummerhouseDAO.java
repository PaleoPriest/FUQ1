/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB_entities.Summerhouse;

import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Shalifar
 */
public interface SummerhouseDAO extends Serializable {
    public List<Summerhouse> getSummerhouseList();
    public void updateSummerhouse(Summerhouse summerhouse);
    public void deleteSummerhouse(int id);
    public void insertSummerhouse(Summerhouse summerhouse);
}
