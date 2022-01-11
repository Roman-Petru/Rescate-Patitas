package domain.models.converters;
import domain.models.modulos.notificador.estrategias.EstrategiaNotificacion;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EstrategiaDeNotificacionConverter implements AttributeConverter<EstrategiaNotificacion, String> {

  @Override
  public String convertToDatabaseColumn(EstrategiaNotificacion estrategiaNotificacion) {
    return estrategiaNotificacion.getClass().getName();
  }

  @Override
  public EstrategiaNotificacion convertToEntityAttribute(String s) {
    EstrategiaNotificacion estrategia = null;
    try {
      Class c = Class.forName(s);
      estrategia = (EstrategiaNotificacion) c.newInstance();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return estrategia;
  }
}