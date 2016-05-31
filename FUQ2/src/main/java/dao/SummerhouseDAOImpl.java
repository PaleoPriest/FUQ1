/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB_entities.Summerhouse;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martynas
 */
@Stateless
public class SummerhouseDAOImpl implements SummerhouseDAO {
    @PersistenceContext(type= PersistenceContextType.TRANSACTION,
            synchronization= SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;
    
    @Override
    public List<Summerhouse> getSummerhouseList() {
        try {
            List<Summerhouse> summerHouseList = em.createQuery("select e from Summerhouse e", Summerhouse.class).getResultList();
            //List<Summerhouse> summerHouseList = em.createQuery("select s from Summerhouse s").getResultList();
            //System.out.println(summerHouseList);
            return summerHouseList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSummerhouse(Summerhouse summerhouse) {

            em.joinTransaction();
            Summerhouse newSummerHouse = em.find(Summerhouse.class, summerhouse.getId());

            if (summerhouse.getName() != null) {
                newSummerHouse.setName(summerhouse.getName());
            }
            if (summerhouse.getDescription() != null) {
                newSummerHouse.setDescription(summerhouse.getDescription());
            }
            if (summerhouse.getMaxRooms() != null) {
                newSummerHouse.setMaxRooms(summerhouse.getMaxRooms());
            }
            if (summerhouse.getReservationStart() != null) {
                newSummerHouse.setReservationStart(summerhouse.getReservationStart());
            }
            if (summerhouse.getReservationTime() != null) {
                newSummerHouse.setReservationTime(summerhouse.getReservationTime());
            }
            if (summerhouse.getUserId() != null) {
                newSummerHouse.setUserId(summerhouse.getUserId());
            }
            em.flush();
        }




    @Override
    public void deleteSummerhouse(int id) {

        try {
            em.remove(em.find(Summerhouse.class, id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void insertSummerhouse(Summerhouse summerhouse) {

        try {
            em.persist(summerhouse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
