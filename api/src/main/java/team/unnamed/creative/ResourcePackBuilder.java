/*
 * This file is part of creative, licensed under the MIT license
 *
 * Copyright (c) 2021-2023 Unnamed Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.unnamed.creative;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.Nullable;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.blockstate.BlockState;
import team.unnamed.creative.font.Font;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.lang.Language;
import team.unnamed.creative.metadata.Metadata;
import team.unnamed.creative.metadata.MetadataPart;
import team.unnamed.creative.metadata.PackMeta;
import team.unnamed.creative.metadata.filter.FilterMeta;
import team.unnamed.creative.metadata.filter.FilterPattern;
import team.unnamed.creative.metadata.language.LanguageEntry;
import team.unnamed.creative.metadata.language.LanguageMeta;
import team.unnamed.creative.model.Model;
import team.unnamed.creative.sound.Sound;
import team.unnamed.creative.sound.SoundRegistry;
import team.unnamed.creative.text.Credits;
import team.unnamed.creative.text.Text;
import team.unnamed.creative.texture.Texture;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ResourcePackBuilder {

    //#region Icon methods
    // |-----------------------------------|
    // |--------- ICON OPERATIONS ---------|
    // |-----------------------------------|
    /**
     * Sets the resource pack icon image
     * (must be a PNG file)
     *
     * @param icon The icon (PNG image)
     * @return This builder, for chaining
     */
    ResourcePackBuilder icon(@Nullable Writable icon);

    /**
     * Returns the resource pack icon image (must
     * be a PNG file), it may be null
     *
     * @return The resource pack icon image, null if
     * not set
     */
    @Nullable Writable icon();
    //#endregion

    //#region Metadata methods
    // |-----------------------------------|
    // |------- METADATA OPERATIONS -------|
    // |-----------------------------------|

    // ----- Pack Meta -----
    ResourcePackBuilder meta(PackMeta meta);

    default ResourcePackBuilder meta(int format, String description) {
        return meta(PackMeta.of(format, description));
    }

    @Nullable PackMeta meta();

    default int format() {
        PackMeta meta = meta();
        if (meta == null) {
            return -1;
        } else {
            return meta.format();
        }
    }

    default @Nullable String description() {
        PackMeta meta = meta();
        if (meta == null) {
            return null;
        } else {
            return meta.description();
        }
    }

    // ----- Language Meta -----
    ResourcePackBuilder languageRegistry(LanguageMeta meta);

    @Nullable LanguageMeta languageRegistry();

    ResourcePackBuilder languageEntry(Key key, LanguageEntry languageEntry);

    @Nullable LanguageEntry languageEntry(Key key);

    Collection<LanguageEntry> languageEntries();

    // ----- Filter Meta -----
    ResourcePackBuilder filter(FilterMeta filter);

    @Nullable FilterMeta filter();

    default ResourcePackBuilder filter(FilterPattern... patterns) {
        return filter(FilterMeta.of(patterns));
    }

    default ResourcePackBuilder filter(List<FilterPattern> patterns) {
        return filter(FilterMeta.of(patterns));
    }

    // ----- Custom Meta -----
    ResourcePackBuilder customMetaPart(MetadataPart part);
    //#endregion

    //#region Blockstate methods
    // |-------------------------------------|
    // |------- BLOCKSTATE OPERATIONS -------|
    // |-------------------------------------|
    ResourcePackBuilder blockState(BlockState state);

    @Nullable BlockState blockState(Key key);

    Collection<BlockState> blockStates();
    //#endregion

    //#region Font methods
    // |-----------------------------------|
    // |--------- FONT OPERATIONS ---------|
    // |-----------------------------------|
    ResourcePackBuilder font(Font font);

    default ResourcePackBuilder font(Key key, FontProvider... providers) {
        return font(Font.of(key, providers));
    }

    default ResourcePackBuilder font(Key key, List<FontProvider> providers) {
        return font(Font.of(key, providers));
    }

    @Nullable Font font(Key key);

    Collection<Font> fonts();
    //#endregion

    ResourcePackBuilder language(Language language);

    Collection<Language> languages();

    ResourcePackBuilder model(Model model);

    Collection<Model> models();

    ResourcePackBuilder sounds(SoundRegistry soundRegistry);

    //#region Sound methods
    // |------------------------------------|
    // |--------- SOUND OPERATIONS ---------|
    // |------------------------------------|
    ResourcePackBuilder sound(Sound.File soundFile);

    default ResourcePackBuilder sound(Key key, Writable data) {
        return sound(Sound.File.of(key, data));
    }
    //#endregion

    //#region Texture methods
    // |------------------------------------|
    // |-------- TEXTURE OPERATIONS --------|
    // |------------------------------------|
    ResourcePackBuilder texture(Texture texture);

    default ResourcePackBuilder texture(Key key, Writable data) {
        return texture(Texture.of(key, data));
    }

    default ResourcePackBuilder texture(Key key, Writable data, Metadata meta) {
        return texture(Texture.of(key, data, meta));
    }

    Collection<Texture> textures();
    //#endregion

    //#region Text methods
    // |----------------------------------|
    // |---------- TEXT METHODS ----------|
    // |----------------------------------|
    default ResourcePackBuilder text(Text text) {
        return file("assets/" + text.key().namespace() + "/texts/" + text.key().value() + ".txt", text.content());
    }

    // ResourcePackBuilder credits(@Nullable Credits credits);

    // @Nullable Credits credits();
    //#endregion

    //#region Shader methods

    //#endregion

    ResourcePackBuilder file(String path, Writable data);

    Map<String, Writable> extraFiles();

}
