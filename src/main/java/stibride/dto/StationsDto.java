/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.dto;

/**
 *
 * @author Ayoub
 */
public class StationsDto extends Dto<Integer> {

    private String name;

    public StationsDto(Integer key) {
        super(key);
    }

    public StationsDto(Integer key, String name) {
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
