import zido from "./zido";

export default {
  install : (app) => {
    app.config.globalProperties.$zido = zido;
}
}