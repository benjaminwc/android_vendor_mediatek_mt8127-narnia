/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*******************************************************************************
 *
 * Filename:
 * ---------
 *   mtk_codec_dummy
 *
 * Project:
 * --------
 *
 *
 * Description:
 * ------------
 *   Audio codec dummy file
 *
 * Author:
 * -------
 * Chipeng Chang
 *
 *------------------------------------------------------------------------------
 * $Revision: #1 $
 * $Modtime:$
 * $Log:$
 *
 *
 *******************************************************************************/


/*****************************************************************************
 *                     C O M P I L E R   F L A G S
 *****************************************************************************/


/*****************************************************************************
 *                E X T E R N A L   R E F E R E N C E S
 *****************************************************************************/

#include <linux/platform_device.h>
#include <linux/slab.h>
#include <linux/module.h>
#include <linux/of_device.h>
#include <sound/core.h>
#include <sound/pcm.h>
#include <sound/soc.h>
#include "AudDrv_Common.h"
#include "mt_soc_analog_type.h"

static int dummy_codec_startup(struct snd_pcm_substream *substream , struct snd_soc_dai *Daiport)
{
    printk("dummy_codec_startup \n");
    return 0;
}

static int dummy_codec_prepare(struct snd_pcm_substream *substream , struct snd_soc_dai *Daiport)
{
    printk("dummy_codec_prepare \n ");
    if (substream->stream == SNDRV_PCM_STREAM_CAPTURE)
    {
        printk("dummy_codec_prepare set up SNDRV_PCM_STREAM_CAPTURE rate = %d\n", substream->runtime->rate);

    }
    else if (substream->stream == SNDRV_PCM_STREAM_PLAYBACK)
    {
        printk("dummy_codec_prepare set up SNDRV_PCM_STREAM_PLAYBACK rate = %d\n", substream->runtime->rate);
    }
    return 0;
}

static int dummy_codec_trigger(struct snd_pcm_substream *substream , int command , struct snd_soc_dai *Daiport)
{
    struct snd_pcm_runtime *runtime = substream->runtime;
    switch (command)
    {
        case SNDRV_PCM_TRIGGER_START:
        case SNDRV_PCM_TRIGGER_RESUME:
        case SNDRV_PCM_TRIGGER_STOP:
        case SNDRV_PCM_TRIGGER_SUSPEND:
            break;
    }

    printk("dummy_codec_trigger command = %d\n ", command);
    return 0;
}

static const struct snd_soc_dai_ops dummy_aif1_dai_ops =
{
    .startup    = dummy_codec_startup,
    .prepare   = dummy_codec_prepare,
    .trigger     = dummy_codec_trigger,
};

static struct snd_soc_dai_driver dummy_6323_dai_codecs[] =
{
    {
        .name = MT_SOC_CODEC_TXDAI_NAME,
        .playback = {
            .stream_name = MT_SOC_DL1_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_RXDAI_NAME,
        .capture = {
            .stream_name = MT_SOC_UL1_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_PCMTXDAI_NAME,
        .playback = {
            .stream_name = MT_SOC_PCM2_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_PCMRXDAI_NAME,
        .capture = {
            .stream_name = MT_SOC_PCM2_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_FMI2S2RXDAI_NAME,
        .playback = {
            .stream_name = MT_SOC_FM_I2S2_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
        .capture = {
            .stream_name = MT_SOC_FM_I2S2_RECORD_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_STUB_NAME,
        .playback = {
            .stream_name = MT_SOC_ROUTING_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
    {
        .name = MT_SOC_CODEC_DUMMY_DAI_NAME,
        .playback = {
            .stream_name = MT_SOC_ROUTING_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
        .capture = {
            .stream_name = MT_SOC_ROUTING_STREAM_NAME,
            .channels_min = 1,
            .channels_max = 2,
            .rates = SNDRV_PCM_RATE_8000_48000,
            .formats = SNDRV_PCM_FMTBIT_S16_LE,
        },
    },
};

static int dummy_codec_probe(struct snd_soc_codec *codec)
{
    printk(codec->dev, "%s()\n", __func__);
    struct snd_soc_dapm_context *dapm = &codec->dapm;

    return 0;
}

static int dummy_codec_remove(struct snd_soc_codec *codec)
{
    printk(codec->dev, "%s()\n", __func__);
    return 0;
}

static int dummy_read(struct snd_soc_codec *codec,
                       unsigned int reg)
{
    printk("dummy_read reg = 0x%x", reg);
}

static int dummy_write(struct snd_soc_codec *codec, unsigned int reg,
                        unsigned int value)
{
    printk("dummy_write reg = 0x%x  value= 0x%x\n", reg, value);
}

static int dummy_Readable_registers()
{
    return 1;
}

static int dummy_volatile_registers()
{
    return 1;
}

static int mt6323_registers_defaults()
{
    return 0;
}

static struct snd_soc_codec_driver soc_mtk_codec =
{
    .probe    = dummy_codec_probe,
    .remove = dummy_codec_remove,

    .read = dummy_read,
    .write = dummy_write,

    .readable_register = dummy_Readable_registers,
    .volatile_register = dummy_volatile_registers,

};

static int mtk_dummy_codec_dev_probe(struct platform_device *pdev)
{
    if (pdev->dev.of_node)
    {
        dev_set_name(&pdev->dev, "%s.%d", "msm-stub-codec", 1);
    }

    dev_err(&pdev->dev, "dev name %s\n", dev_name(&pdev->dev));

    return snd_soc_register_codec(&pdev->dev,
                                  &soc_mtk_codec, dummy_6323_dai_codecs, ARRAY_SIZE(dummy_6323_dai_codecs));
}

static int mtk_dummy_codec_dev_remove(struct platform_device *pdev)
{
    printk("%s:\n", __func__);

    snd_soc_unregister_codec(&pdev->dev);
    return 0;

}

static struct platform_driver mtk_codec_dummy_driver =
{
    .driver = {
        .name = MT_SOC_CODEC_DUMMY_NAME,
        .owner = THIS_MODULE,
    },
    .probe  = mtk_dummy_codec_dev_probe,
    .remove = mtk_dummy_codec_dev_remove,
};

static struct platform_device *soc_mtk_codec_dummy_dev;

static int __init mtk_dummy_codec_init(void)
{
    printk("%s:\n", __func__);
    int ret;
    soc_mtk_codec_dummy_dev = platform_device_alloc(MT_SOC_CODEC_DUMMY_NAME, -1);
    if (!soc_mtk_codec_dummy_dev)
    {
        return -ENOMEM;
    }

    ret = platform_device_add(soc_mtk_codec_dummy_dev);
    if (ret != 0)
    {
        platform_device_put(soc_mtk_codec_dummy_dev);
        return ret;
    }

    return platform_driver_register(&mtk_codec_dummy_driver);
}
module_init(mtk_dummy_codec_init);

static void __exit mtk_codec_dummy_exit(void)
{
    printk("%s:\n", __func__);

    platform_driver_unregister(&mtk_codec_dummy_driver);
}

module_exit(mtk_codec_dummy_exit);

/* Module information */
MODULE_DESCRIPTION("MTK  dummy codec driver");
MODULE_LICENSE("GPL v2");

