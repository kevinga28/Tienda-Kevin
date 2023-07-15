package com.tienda.service.impl;

//import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.DocumentSnapshot;
//import com.google.cloud.firestore.QuerySnapshot;
//import com.google.cloud.firestore.WriteResult;
import com.tienda.service.IBaseService;
//import com.tienda.entities.Product;
//import com.tienda.FirebaseConfig;
//import java.util.ArrayList;
//import java.util.HashMap;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
//import java.util.Map;
import java.util.Optional;
//import org.checkerframework.checker.units.qual.t;
//import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T, Y> implements IBaseService<T, Y> {
    protected final CrudRepository<T, Y> repository;

    public BaseService(CrudRepository<T, Y> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return (List<T>) this.repository.findAll();
    }

    public T save(T item) {
//         Map<String, Object> docData = getDocData(item);
//
//        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
//
//        try {
//            if(null != writeResultApiFuture.get()){
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        } catch (Exception e) {
//            return Boolean.FALSE;
//        }
        return this.repository.save(item);
    }

    public Optional<T> getById(Y id) {
        return this.repository.findById(id);
    }

    public void delete(T item) {
//         ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(item).delete();
//        try {
//            if(null != writeResultApiFuture.get()){
//                return Boolean.TRUE;
//            }
//            return Boolean.FALSE;
//        } catch (Exception e) {
//            return Boolean.FALSE;
//        }
        this.repository.delete(item);
    }
//     @Autowired
//    private FirebaseConfig firebase;

//
//    public List<Product> list() {
//        List<Product> response = new ArrayList<>();
//        Product p;
//
//        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
//        try {
//            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
//                p = doc.toObject(Product.class);
//                response.add(p);
//            }
//            return response;
//        } catch (Exception e) {
//            return null;
//        }
//    }



//    private Map<String, Object> getDocData(Product p) {
//        Map<String, Object> docData = new HashMap<>();
//        docData.put("Category", p.getCategory());
//        docData.put("Descripcion", p.getDescripcion());
//          docData.put("Category", p.getDetalle());
//        docData.put("Descripcion", p.getExistencias());
//          docData.put("Category", p.getPrecio());
//        docData.put("Descripcion", p.getRuta_imagen());
//        return docData;
//    }
}
