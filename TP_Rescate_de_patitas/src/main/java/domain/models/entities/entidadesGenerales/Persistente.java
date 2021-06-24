package domain.models.entities.entidadesGenerales;


    public abstract class Persistente {
        private Integer id;

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

    }
