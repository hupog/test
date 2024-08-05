package org.example.services;

import org.example.domain.Empleado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {
    private List<Empleado> repositorio = new ArrayList<Empleado>();

    public List<Empleado> obtenerTodos(){
        return repositorio;
    }

    public Empleado obtenerPorId(Long id) throws RuntimeException{
        for(Empleado emp: repositorio){
            if(emp.getId() == id){
                return emp;
            }
        }
        throw new RuntimeException("No se encontro el id de la empleado");
    }

    public Empleado añadir(Empleado emp)throws RuntimeException{
        if(repositorio.contains(emp))
            throw new RuntimeException("Ya existe un empleado con ese nombre");
        repositorio.add(emp);
        return emp;
    }

    public Empleado editar(Empleado emp) throws RuntimeException{
        int pos = repositorio.indexOf(emp);
        if(pos == -1)
            throw new RuntimeException("No se encontro el empleado");
        repositorio.set(pos, emp);
        return emp;
    }

    public void borrar(Long id) throws RuntimeException{
        Empleado emp = this.obtenerPorId(id);
        if(emp != null) {
            repositorio.remove(emp);
        }else{
            throw new RuntimeException("No se encontro el registro");
        }
    }
}
