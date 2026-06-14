package com.korsh.components;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public interface KorshComponents {
  static Component usuallyComponent(String text){
    //Components
    return Component.text(text)
      .color(TextColor.color(255,255,255))
      .decorate(TextDecoration.ITALIC, TextDecoration.BOLD)
      .decoration(TextDecoration.OBFUSCATED, false);
  }

  static Component projectComponent(){
    String linkProject = "https://github.com/Fanzi03/KorshPlugin";
    return Component.text("Оцени проект, кликни по сообщению!")
        .color(TextColor.color(255,20,20))
        .hoverEvent(HoverEvent.showText(Component.text("Проект растет!")))
        .hoverEvent(HoverEvent.showItem(Key.key(Key.MINECRAFT_NAMESPACE, "diamond"), 55))
        .clickEvent(ClickEvent.copyToClipboard(linkProject))
        .clickEvent(ClickEvent.openUrl(linkProject));
  }

  static Component suprizeComponent(){
    return Component.text("Не нажимай на эту кнопку")
      .clickEvent(ClickEvent.runCommand("/kill"));
  }
  
}
