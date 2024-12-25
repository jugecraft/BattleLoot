# BattleLoot - Plugin de Minecraft

**BattleLoot** es un plugin para servidores de Minecraft que agrega una modalidad especial en la que se generan cofres aleatorios con loot en el mundo. Los jugadores pueden encontrar estos cofres durante sus partidas, obteniendo recursos valiosos. Además, el plugin está en constante desarrollo y se encuentra en **Beta**.

### Características:

- **Generación de cofres aleatorios:** El plugin coloca cofres con loot distribuido de forma aleatoria en el mapa para que los jugadores los encuentren.
- **Configuración de loot:** El loot es configurable a través de un archivo de configuración. Puedes definir diferentes tipos de loot que se generarán en los cofres.
- **Destrucción automática:** Los cofres tienen una vida útil, es decir, después de un cierto tiempo, se destruirán automáticamente.
- **Scoreboard de jugador:** Muestra estadísticas del jugador, como el nombre, el rango, nivel, dinero, ping, y la IP en un scoreboard visual.

## Instalación:

### Requisitos:
- Un servidor de Minecraft (recomendado Spigot o Paper).
- Java 8 o superior.

### Pasos para instalar:
1. Descarga el archivo `BattleLoot.jar`.
2. Colócalo en la carpeta `plugins` de tu servidor de Minecraft.
3. Reinicia tu servidor para que el plugin se cargue correctamente.

## Comandos:

### Comandos disponibles:
- **/spawnchest <x> <y> <z>**
   - Este comando genera un cofre aleatorio en las coordenadas especificadas.
   - **Ejemplo:** `/spawnchest 100 64 200`

### Configuración de loot:
Los cofres contienen objetos aleatorios que pueden configurarse. El tipo de loot está determinado por la opción `loot.type` en el archivo de configuración.

#### Tipos de loot:
- **default:** Items básicos como espada de hierro, manzana dorada, y lingotes de hierro.
- **advanced:** Items más avanzados como espada de diamante, manzanas doradas y libros encantados.
- **epic:** Loot épico, incluye estrellas del Nether, manzanas doradas y objetos raros.

Los cofres se generarán con uno de estos sets de loot, según la configuración.

### Configuración de tiempo de destrucción:
El tiempo en que el cofre permanecerá en el mundo antes de ser destruido es configurable a través de `loot.destructionTime`. El tiempo se define en segundos.

## Funcionalidades adicionales:
### Scoreboard:
Cuando un jugador entra en el servidor, se muestra un **scoreboard** en la parte lateral de la pantalla con las siguientes estadísticas:
- **Nombre del servidor**: El nombre de tu servidor.
- **Nombre del jugador**: El nombre del jugador actual.
- **Rango**: El rango del jugador (si está configurado en tu servidor).
- **Nivel**: El nivel del jugador, si se está utilizando un sistema de niveles.
- **Dinero**: El dinero del jugador, si usas un plugin de economía como Essentials.
- **Ping**: El ping del jugador.
- **IP**: La dirección IP del jugador.

### Personalización:
El plugin es totalmente configurable para adaptarse a las necesidades de tu servidor. Modifica los parámetros en el archivo `config.yml` para ajustar el tipo de loot, tiempos de destrucción y otros aspectos importantes del juego.

## Estado del proyecto:
**BattleLoot** se encuentra actualmente en **Beta**. Algunas funcionalidades pueden no estar completamente implementadas o pueden estar sujetas a cambios importantes.

### Planes a futuro:
- Mejoras en la aleatorización del loot.
- Adición de nuevos tipos de cofres y mecánicas.
- Mejoras de rendimiento para servidores con alta cantidad de jugadores.

## Contribuciones:

Si deseas contribuir al proyecto, ¡bienvenido! Para hacerlo, realiza un **Fork** de este repositorio, realiza tus mejoras y abre un **Pull Request**. Asegúrate de que tu código esté correctamente documentado y sigua las buenas prácticas de programación.

## Licencia:

Este proyecto está bajo la **Licencia MIT**. Puedes ver el texto completo de la licencia en el archivo `LICENSE`.

---

Gracias por usar **BattleLoot**. Si tienes alguna sugerencia o encuentras algún error, no dudes en crear un **issue** o contactarnos directamente.

