package br.com.sgcraft.minigladiador.chat;

import br.com.devpaulo.legendchat.api.events.*;
import br.com.sgcraft.minigladiador.mensagens.*;

import org.bukkit.event.*;

public class LegendChat implements Listener
{
    @EventHandler
    private void onChat(final ChatMessageEvent e) {
        if (Mensagens.containsVencedor(e.getSender().getName()) && e.getTags().contains("minigladiador")) {
            e.setTagValue("minigladiador", Mensagens.getTag());
        }
    }
}
