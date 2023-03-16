package com.plataforamasHeterogeneas.proyectoAPI.controller;

import com.plataforamasHeterogeneas.proyectoAPI.model.Alumno;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ApiDemo {
    static ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    @GetMapping ("/saludar")
    public String saludar(){

        return "Este es un demo en API";
    }

    @GetMapping("/llenar")
    public String llenar(){
        alumnos.add(new Alumno(1,"Omar Ramirez","Direccion 1","omar.rm02@gmail.com"));
        alumnos.add(new Alumno(3,"Hermak Banda","Direccion 2","hermak@gmail.com"));
        alumnos.add(new Alumno(2,"Leonardo Cruz","Direccion 3","leo@gmail.com"));

        return "todos llenos";
    }
    @GetMapping("/all")
    public ArrayList<Alumno> getAlumno(){
        return alumnos;
    }

    @GetMapping("/find/{id}")
    public Alumno getAlumnoById(@PathVariable("id") long id){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId()==id){
                alumno=a;
            }
        }
        return alumno;
    }
    @PostMapping("/save")
    public Alumno insertarAlumno(@RequestBody Alumno alumno) throws Exception{
        if(getAlumnoById(alumno.getId())!=null)
            return alumno;
        alumnos.add(alumno);
        return alumnos.get(alumnos.size()-1);
    }

    @DeleteMapping("delete/{id}")
    public Alumno deleteAlumno(@PathVariable("id") long id){
        Alumno alumno = getAlumnoById(id);
        alumnos.remove(alumno);
        return alumno;
    }

    @PutMapping("/update/{id}/name/{name}")
    public Alumno updateNombrebyId(@PathVariable("id" ) long id, @PathVariable("name") String name){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId()==id){
                a.setNombre(name);
                alumno=a;
            }
        }
        return alumno;
    }

    @PutMapping("/update")
    public Alumno updateAll(@RequestBody Alumno al){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId()==al.getId()){
                a.setNombre(al.getNombre());
                a.setDireccion(al.getDireccion());
                a.setCorreo(al.getCorreo());
                alumno=a;
            }
        }
        return alumno;
    }
    @GetMapping("/saveToFile")
    public void save(){
        try{
            FileOutputStream fout = new FileOutputStream("temporal.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(alumnos);
            fout.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @GetMapping("/readFromFile")
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("temporal.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        alumnos = (ArrayList<Alumno>)ois.readObject();
        fin.close();
    }


    @PostMapping("/insertar")
    public void insertar(@RequestBody Alumno al){
        for(Alumno a: alumnos) {
            if (a.getId() == al.getId()) {
                break;
            }
        }
        alumnos.add(new Alumno(al.getId(),al.getNombre(),al.getDireccion(),al.getCorreo()) );
    }
   /* @GetMapping("/readFromFile2")
    public ArrayList<Alumno> read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("temporal.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        alumnos = (ArrayList<Alumno>)ois.readObject();
        fin.close();

        return alumnos;
    } */
}
