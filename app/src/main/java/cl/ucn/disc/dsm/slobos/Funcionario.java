package cl.ucn.disc.dsm.slobos;

import lombok.Builder;
import lombok.Getter;

/**
 * The model of  Funcionario
 *
 *
 * @author Sebastian Lobos
 */
@Builder
public final class Funcionario {

    /**
     * The ID.
     */
    @Getter
    private int id;
    /**
     * The Nombre
     */
    @Getter
    private String nombre;
    /**
     * The Cargo.
     */

    @Getter
    private String cargo;

    /**
     * The Unidad.
     */
    @Getter
    private String unidad;

    /**
     * The Email
     */

    @Getter
    private String email;
    /**
     * The Telefono.
     */

    @Getter
    private String telefono;

    /**
     * The Oficina.
     */

    @Getter
    private String oficina;

    /**
     * The Direccion.
     */
    @Getter
    private String direccion;

}
